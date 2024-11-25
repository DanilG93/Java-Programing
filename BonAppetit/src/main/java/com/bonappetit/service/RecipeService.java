package com.bonappetit.service;

import com.bonappetit.model.DTO.AddRecipeDTO;
import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RecipeService {

    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    public void addedRecipe(@Valid AddRecipeDTO addRecipeDTO, Authentication authentication) {

        String name = authentication.getName();
        User byUsername = userRepository.findByUsername(name).orElse(null);


        Recipe build = Recipe.builder()
                .name(addRecipeDTO.getName())
                .ingredients(addRecipeDTO.getIngredients())
                .category(addRecipeDTO.getCategory())
                .addedBy(byUsername)
                .build();

        recipeRepository.save(build);

    }

    public List<Recipe> findAllByCategoryName(CategoryName categoryName) {
        return recipeRepository.findAllByCategoryName(categoryName);
    }


    public Recipe finnById(Long recipeId) {

        return recipeRepository.findById(recipeId).orElse(null);
    }
}
