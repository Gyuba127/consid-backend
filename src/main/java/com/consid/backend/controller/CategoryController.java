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
        if(categoryService.saveCategory(categoryName)) return ResponseEntity.ok(categoryName + " has been saved.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(categoryName+ " is already saved");
    }

    @PutMapping("/category/update/{id}/{categoryName}/{updateString}")
    public ResponseEntity<?> updateCategory(@PathVariable int id, @PathVariable String categoryName, @PathVariable String updateString){
        if(categoryService.updateCategory(new Category(id,categoryName), updateString)) return ResponseEntity.ok("Category has been updated.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not update since: "+updateString+ " is already saved");
    }


    @GetMapping("/category/all")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/category/delete/{id}/{categoryName}")
    public ResponseEntity<?> deleteCategory(@PathVariable int id, @PathVariable String categoryName){
        if(categoryService.deleteCategory(new Category(id,categoryName))) return ResponseEntity.ok("Category has been deleted.");
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not delete since: "+categoryName+ " is referenced");
    }
}
