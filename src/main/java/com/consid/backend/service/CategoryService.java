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

    /**
     * Method for saving a new category.
     * Can only save a new category if it's unique.
     * @param category
     * @return returns a boolean to indicate if new category is saved correctly.
     */
    @Override
    public boolean saveCategory(String category) {
        if(!categoryRepository.existsBycategoryName(category)){
            categoryRepository.save(new Category(category));
            return true;
        }

       else return false;
    }

    /**
     * Method for updating a specific category.
     * Can only update the category if new category is unique and current category id is present in db.
     * @param category
     * @param updateString
     * @return returns a boolean to indicate if category update to db happened correctly.
     */
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

    /**
     * Method for deleting a specific category
     * Category can only be deleted if it's not referenced in any item.
     * @param category
     * @return returns a boolean to indicate if category deletion happened correctly.
     */
    @Override
    public boolean deleteCategory(Category category) {
        if(!libraryItemRepository.existsByCategoryId(category)){
            categoryRepository.delete(category);
            return true;
        }
        return false;
    }

    /**
     * Method for retrieving all categories from db.
     * @return returns all categories in a list.
     */
    public List<Category> getCategories(){
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
