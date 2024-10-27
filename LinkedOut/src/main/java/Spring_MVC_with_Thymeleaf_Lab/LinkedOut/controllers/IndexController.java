package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }
}
