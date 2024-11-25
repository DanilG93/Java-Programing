package com.bonappetit.controller;

import com.bonappetit.model.entity.CategoryName;
import com.bonappetit.service.RecipeService;
import com.bonappetit.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class HomeController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String getIndexPage(Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String getHomePage(Model model, Authentication authentication) {

        model.addAttribute("username", authentication.getName());

        model.addAttribute("desserts", recipeService.findAllByCategoryName(CategoryName.DESSERT));
        model.addAttribute("cocktails", recipeService.findAllByCategoryName(CategoryName.COCKTAIL));
        model.addAttribute("mainDishes", recipeService.findAllByCategoryName(CategoryName.MAIN_DISH));
        model.addAttribute("favourites", userService.findByUsername(authentication.getName()).getFavouriteRecipes());

        return "home";
    }

}
