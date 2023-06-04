package com.helpdeskonboot.helpdesk.service;

import com.helpdeskonboot.helpdesk.model.Category;

import java.util.List;

public interface CategoryService {
    List<String> getAllCategories();

    Category getByName(String categoryName);
}
