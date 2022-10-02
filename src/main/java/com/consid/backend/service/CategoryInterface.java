package com.consid.backend.service;

import com.consid.backend.models.Category;

import java.sql.SQLException;

/**
 * Interface declaration for core methods for Category
 */
public interface CategoryInterface {
    public boolean saveCategory(String category);
    public boolean updateCategory(Category category, String updateString);
    public boolean deleteCategory(Category category) throws SQLException;
}
