package com.consid.backend.repository;

import com.consid.backend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Boolean existsBycategoryName(String name);
}
