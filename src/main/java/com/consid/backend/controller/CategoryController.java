package com.consid.backend.controller;

import com.consid.backend.models.Category;
import com.consid.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/add/{categoryName}")
    public ResponseEntity<?> createCategory(@PathVariable String categoryName){
        try{
            if(categoryService.saveCategory(categoryName)) return ResponseEntity.ok(categoryName + " has been saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categoryName+ " is already saved");
    }

    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }
}
