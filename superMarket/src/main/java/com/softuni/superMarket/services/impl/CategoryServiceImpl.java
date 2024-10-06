package com.softuni.superMarket.services.impl;

import com.softuni.superMarket.db.entity.Category;
import com.softuni.superMarket.db.repositories.CategoryRepository;
import com.softuni.superMarket.services.CategoryService;
import com.softuni.superMarket.util.ValidatorUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    private ValidatorUtil validatorUtil;


    @Override
    @Transactional
    public String addCategory(String name) {
        StringBuilder sb = new StringBuilder();

        if (findByName(name) != null) {
            return sb.append("Category already exists!").toString();
        }

        Category category = new Category(name);
        category.setId(UUID.randomUUID().toString());

        if (validatorUtil.isValid(category)) {
            sb.append("Successfully added category!").append(System.lineSeparator());
            categoryRepository.save(category);
        } else {
            Set<ConstraintViolation<Category>> violations = validatorUtil.getViolations(category);
            violations.forEach(v -> sb.append(v.getMessage()).append(System.lineSeparator()));
        }

        return sb.toString().trim();
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
