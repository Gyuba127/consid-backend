package com.consid.backend.service;

import com.consid.backend.models.LibraryItem;
import com.consid.backend.repository.CategoryRepository;
import com.consid.backend.repository.LibraryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryItemService implements LibraryItemInterface{
    @Autowired
    LibraryItemRepository libraryItemRepository;
    @Override
    public boolean saveLibraryItem(LibraryItem libraryItem) {
        try{
            libraryItemRepository.save(libraryItem);
            return true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateLibraryItem(LibraryItem libraryItem, int libId) {
        return false;
    }

    @Override
    public boolean deleteLibraryItem(LibraryItem libraryItem) {
        return false;
    }

    @Override
    public boolean checkInItem(LibraryItem libraryItem) {
        return false;
    }

    @Override
    public boolean checkOutItem(LibraryItem libraryItem) {
        return false;
    }
}
