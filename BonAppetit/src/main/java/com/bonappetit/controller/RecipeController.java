package com.bonappetit.controller;

import com.bonappetit.model.DTO.AddRecipeDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.service.CategoryService;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping("/recipe")
@AllArgsConstructor

public class RecipeController {

    private CategoryService categoryService;
    private RecipeService recipeService;
    private UserService userService;

    @GetMapping("/add")
    public String addRecipePage(Model model) {

        model.addAttribute("addRecipeDTO", new AddRecipeDTO());
        model.addAttribute("categories", categoryService.findAll());

        return "recipe-add";
    }

    @PostMapping("/add")
    public String addRecipe(@Valid @ModelAttribute("addRecipeDTO") AddRecipeDTO addRecipeDTO,
                            BindingResult bindingResult,
                            Model model,
                            RedirectAttributes redirectAttributes,
                            Authentication authentication) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("categories", categoryService.findAll());
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addRecipeDTO", bindingResult);
            redirectAttributes.addFlashAttribute("addRecipeDTO", addRecipeDTO);

            return "recipe-add";
        }

        recipeService.addedRecipe(addRecipeDTO, authentication);

        return "redirect:/home";
    }

    @Transactional
    @PostMapping("/add-to-favorites/{recipeId}")
    public String addToFavorites(@PathVariable Long recipeId, Authentication authentication) {
        User authorizedUser = userService.findByUsername(authentication.getName());
        Recipe recipe = recipeService.finnById(recipeId);
        authorizedUser.getFavouriteRecipes().add(recipe);
        userService.save(authorizedUser);
        return "redirect:/home";
    }

    @PostMapping("/remove-from-favorites/{recipeId}")
    public String removeFromFavorites(@PathVariable Long recipeId, Authentication authentication,Model model) {

        model.addAttribute("favourites", userService.findByUsername(authentication.getName()).getFavouriteRecipes());
        userService.removeRecipeFromFavorites(recipeId, authentication);
        return "redirect:/home";
    }


}
