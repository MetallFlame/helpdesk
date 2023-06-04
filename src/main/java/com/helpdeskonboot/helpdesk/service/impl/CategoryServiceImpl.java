package com.helpdeskonboot.helpdesk.service.impl;

import com.helpdeskonboot.helpdesk.exception.CategoryNotFoundException;
import com.helpdeskonboot.helpdesk.model.Category;
import com.helpdeskonboot.helpdesk.repository.CategoryRepository;
import com.helpdeskonboot.helpdesk.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<String> getAllCategories() {
        return categoryRepository.getAll().stream().map(category -> category.getName()).collect(Collectors.toList());
    }

    @Override
    public Category getByName(String categoryName) {
        return categoryRepository.getByName(categoryName).orElseThrow(CategoryNotFoundException::new);
    }
}
