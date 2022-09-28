package com.consid.backend.repository;

import com.consid.backend.models.Category;
import com.consid.backend.models.LibraryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryItemRepository extends JpaRepository<LibraryItem, Integer> {
    Boolean existsByCategoryId(Category category);
}
