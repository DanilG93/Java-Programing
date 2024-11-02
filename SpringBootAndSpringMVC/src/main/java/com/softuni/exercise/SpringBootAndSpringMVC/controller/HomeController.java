package com.softuni.exercise.SpringBootAndSpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // This should match the name of your HTML file in `templates/`
    }
}
