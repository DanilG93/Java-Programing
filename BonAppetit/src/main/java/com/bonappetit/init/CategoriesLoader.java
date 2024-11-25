package com.bonappetit.init;

import com.bonappetit.model.entity.Category;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.repo.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CategoriesLoader implements CommandLineRunner {


    private final Map<CategoryName,String> categoryNameStringMap = Map.of(
            CategoryName.COCKTAIL,"Sip of sophistication, cocktails blend flavors, creating a spirited symphony in every glass.",
            CategoryName.DESSERT,"Sweet finale, indulgent and delightful; dessert crowns the dining experience with joy.",
            CategoryName.MAIN_DISH,"Heart of the meal, substantial and satisfying; main dish delights taste buds."
    );

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {

        long count = categoryRepository.count();

        if (count > 0) {
            return;
        }

        List<Category> categoryList = categoryNameStringMap.keySet()
                .stream()
                .map(categoryName ->
                        Category.builder()
                                .name(categoryName)
                                .description(categoryNameStringMap.get(categoryName))
                                .build()
                ).toList();

        categoryRepository.saveAll(categoryList);
    }
}
