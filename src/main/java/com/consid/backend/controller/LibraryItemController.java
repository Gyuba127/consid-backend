package com.consid.backend.controller;

import com.consid.backend.models.LibraryItem;
import com.consid.backend.service.LibraryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
