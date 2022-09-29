package com.consid.backend.service;

import com.consid.backend.models.LibraryItem;

public interface LibraryItemInterface {
    public boolean saveLibraryItem(LibraryItem libraryItem);
    public boolean updateLibraryItem(LibraryItem libraryItem);
    public boolean deleteLibraryItem(int id);
    public boolean checkInItem(LibraryItem libraryItem);
    public boolean checkOutItem(LibraryItem libraryItem, String borrower);
}
