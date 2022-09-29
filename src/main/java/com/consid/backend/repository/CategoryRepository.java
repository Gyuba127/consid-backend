package com.consid.backend.repository;

import com.consid.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsBycategoryName(String name);
    boolean existsById(int id);
}
