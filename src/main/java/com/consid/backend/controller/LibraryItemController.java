package com.consid.backend.controller;

import com.consid.backend.models.LibraryItem;
import com.consid.backend.service.LibraryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LibraryItemController {
    @Autowired
    LibraryItemService libraryItemService;


    @PostMapping("/library/add")
    public ResponseEntity<?> createItem(@RequestBody LibraryItem libraryItem){
        if(libraryItemService.saveLibraryItem(libraryItem)) return ResponseEntity.ok(libraryItem.getTitle() + " has been saved.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something happened");
    }

    @DeleteMapping("library/delete/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable int id){
        if(libraryItemService.deleteLibraryItem(id)) return ResponseEntity.ok("Item has been deleted.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such item to delete.");
    }

    @PutMapping("library/checkout/{borrowerName}")
    public ResponseEntity<?> checkOutItem(@RequestBody LibraryItem libraryItem, @PathVariable String borrowerName){
        if(libraryItemService.checkOutItem(libraryItem, borrowerName)) return ResponseEntity.ok(libraryItem.getTitle() + " has been checked out.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something happened.");
    }

    @PutMapping("library/checkin")
    public ResponseEntity<?> checkInItem(@RequestBody LibraryItem libraryItem){
        if(libraryItemService.checkInItem(libraryItem)) return ResponseEntity.ok(libraryItem.getTitle() + " has been checked out.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something happened.");
    }

    @PutMapping("library/update/")
    public ResponseEntity<?> updateItem(@RequestBody LibraryItem libraryItem){
        if(libraryItemService.updateLibraryItem(libraryItem)) return ResponseEntity.ok("Item has been checked out.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something happened.");
    }
}
