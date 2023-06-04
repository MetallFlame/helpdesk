package com.helpdeskonboot.helpdesk.repository;

import com.helpdeskonboot.helpdesk.model.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> getAll();

    Optional<Category> getByName(String categoryName);
}
