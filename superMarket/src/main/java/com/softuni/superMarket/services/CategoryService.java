package com.softuni.superMarket.services;

import com.softuni.superMarket.db.entity.Category;
import org.springframework.stereotype.Service;

import java.util.Stack;

@Service
public interface CategoryService {

    String addCategory(String name);
    Category findByName(String name);
}

