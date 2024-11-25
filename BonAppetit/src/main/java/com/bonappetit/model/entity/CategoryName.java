package com.bonappetit.model.entity;

import lombok.Getter;


@Getter
public enum CategoryName {

    MAIN_DISH("Main Dish"),
    DESSERT("Dessert"),
    COCKTAIL("Cocktail");

    private final String name;


    CategoryName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}


