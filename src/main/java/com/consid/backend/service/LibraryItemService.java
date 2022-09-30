package com.consid.backend.service;

import com.consid.backend.models.LibraryItem;
import com.consid.backend.repository.LibraryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class LibraryItemService implements LibraryItemInterface{
    @Autowired
    LibraryItemRepository libraryItemRepository;
    @Override
    public boolean saveLibraryItem(LibraryItem libraryItem) {
        try{
            String title = libraryItem.getTitle();
            String[] words = title.split(" ");
            if(words.length >= 2){
                String acronym = acronymTitle(words);
                String newTitle = title + " ("+acronym+")";
                libraryItem.setTitle(newTitle);
                libraryItemRepository.save(libraryItem);
            }
            else libraryItemRepository.save(libraryItem);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Transactional
    @Override
    public boolean updateLibraryItem(LibraryItem updatedItem) {
        if(libraryItemRepository.existsById(updatedItem.getId())){
            libraryItemRepository.save(updatedItem);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLibraryItem(int id) {
        if(libraryItemRepository.existsById(id)){
            libraryItemRepository.deleteById(id);
            return true;
        }
        return false;
    }

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

    // only use when there's more than one word?
    private String acronymTitle(String[] words){
        String acronym = "";
        for(String word : words){
            acronym += Character.toUpperCase(word.charAt(0));
        }
        return acronym;
    }

    public List<LibraryItem> getItems(){
        List<LibraryItem> items = libraryItemRepository.findAll();
        return items;
    }
}
