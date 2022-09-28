package com.consid.backend.service;

import com.consid.backend.models.Category;
import com.consid.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService implements CategoryInterface{
    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public boolean saveCategory(String category) {
        System.out.println("in save");
        if(!categoryRepository.existsBycategoryName(category)){
            System.out.println("does not exsist");
            categoryRepository.save(new Category(category));
            return true;
        }

       else return false;
    }

    @Override
    public boolean updateCategory(String category) {
        return false;
    }

    @Override
    public boolean deleteCategory(String category) throws SQLException {
        return false;
    }

    public List<Category> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
