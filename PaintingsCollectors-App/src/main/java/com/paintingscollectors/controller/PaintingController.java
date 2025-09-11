package com.paintingscollectors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/painting")
public class PaintingController {

    @GetMapping("/add")
    public String getAddPage() {
        return "add-painting";
    }
}
