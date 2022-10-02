package com.consid.backend.service;

import com.consid.backend.models.LibraryItem;
import com.consid.backend.repository.LibraryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class LibraryItemService implements LibraryItemInterface{
    @Autowired
    LibraryItemRepository libraryItemRepository;

    /**
     * Method for saving a new library item to db.
     * @param libraryItem
     * @return returns a boolean to indicate if item saved to db happened correctly.
     */
    @Override
    public boolean saveLibraryItem(LibraryItem libraryItem) {
        try{
            acronymTitle(libraryItem);
            libraryItemRepository.save(libraryItem);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Method for updating a specific item with new data.
     * @param updatedItem
     * @return returns a boolean to indicate if item update happened correctly.
     */
    @Transactional
    @Override
    public boolean updateLibraryItem(LibraryItem updatedItem) {
        if(libraryItemRepository.existsById(updatedItem.getId())){
            acronymTitle(updatedItem);
            libraryItemRepository.save(updatedItem);
            return true;
        }
        return false;
    }

    /**
     * Method for removing an item from db by id if it exists.
     * @param id
     * @return returns a boolean to indicate if item deletion happened correctly
     */
    @Override
    public boolean deleteLibraryItem(int id) {
        if(libraryItemRepository.existsById(id)){
            libraryItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Method for item check-in only if item is borrowed.
     * Item information such as; borrower, isBorrowable and borrowDate is updated.
     * @param libraryItem
     * @return returns a boolean to indicate if check-in happened correctly
     */
    @Transactional
    @Override
    public boolean checkInItem(LibraryItem libraryItem) {
        if(!libraryItem.getisBorrowable() && libraryItemRepository.existsById(libraryItem.getId())){
            libraryItem.setisBorrowable(true);
            libraryItem.setBorrower(null);
            libraryItem.setBorrowDate(null);
            libraryItemRepository.save(libraryItem);
            return true;
        }
        return false;
    }

    /**
     * Method for item check-out only if item is borrowable.
     * Item information such as; borrower, isBorrowable and borrowDate is updated.
     * @param libraryItem
     * @param borrower
     * @return returns a boolean to indicate if check-out happened correctly
     */
    @Transactional
    @Override
    public boolean checkOutItem(LibraryItem libraryItem, String borrower) {
        if(libraryItem.getisBorrowable() && libraryItemRepository.existsById(libraryItem.getId())){
            libraryItem.setBorrower(borrower);
            libraryItem.setBorrowDate(new Date());
            libraryItem.setisBorrowable(false);
            libraryItemRepository.save(libraryItem);
            return true;
        }
        return false;
    }

    /**
     * Method for adding acronym to item title.
     * Only adds acronym on titles with more than one word.
     * @param libraryItem
     */
    private void acronymTitle(LibraryItem libraryItem){
        String title = libraryItem.getTitle();
        String[] words = title.split(" ");
        if(words.length >= 2){
            String acronym = "";
            for(String word : words){
                acronym += Character.toUpperCase(word.charAt(0));
            }
            String newTitle = title + " ("+acronym+")";
            libraryItem.setTitle(newTitle);
        }
    }

    /**
     * Gets all items from the db.
     * @return Returns a list with all the items sorted by category name.
     */
    public List<LibraryItem> getItems(){
        List<LibraryItem> items = libraryItemRepository.findAll();
        items.sort(new Comparator<LibraryItem>() {
            @Override
            public int compare(LibraryItem o1, LibraryItem o2) {
                return o1.getCategoryId().getCategoryName().compareTo(o2.getCategoryId().getCategoryName());
            }
        });
        return items;
    }
}
