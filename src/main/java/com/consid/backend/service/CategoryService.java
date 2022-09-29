package com.consid.backend.service;

import com.consid.backend.models.Category;
import com.consid.backend.repository.CategoryRepository;
import com.consid.backend.repository.LibraryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService implements CategoryInterface{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LibraryItemRepository libraryItemRepository;


    @Override
    public boolean saveCategory(String category) {
        if(!categoryRepository.existsBycategoryName(category)){
            categoryRepository.save(new Category(category));
            return true;
        }

       else return false;
    }
    // check this method
    @Transactional
    @Override
    public boolean updateCategory(Category category, String updateString) {
        if(!categoryRepository.existsBycategoryName(updateString) && categoryRepository.existsById(category.getId())){
            category.setCategoryName(updateString);
            categoryRepository.save(category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCategory(Category category) {
        if(!libraryItemRepository.existsByCategoryId(category)){
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }

    public List<Category> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
