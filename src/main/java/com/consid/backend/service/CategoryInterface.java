package com.consid.backend.service;

import java.sql.SQLException;

/**
 * Interface declaration for core methods
 */
public interface CategoryInterface {
    public boolean saveCategory(String category);
    public boolean updateCategory(String category);
    public boolean deleteCategory(String category) throws SQLException;
}
