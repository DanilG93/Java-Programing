package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.DTO.UserLoginDTO;
import com.dictionaryapp.model.DTO.UserRegistrationDTO;
import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private UserSession userSession;

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("userLoginDTO", new UserLoginDTO());

        if (userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "login";
    }

    @PostMapping("/login")
    public String LoginIn(@Valid @ModelAttribute UserLoginDTO userLoginDTO, BindingResult bindingResult) {

        User byUsername = userService.findByUsername(userLoginDTO.getUsername());

        if (byUsername == null) {
            bindingResult.rejectValue("username","error.user","Username or password not matched!!");
        } else {
            if (!userLoginDTO.getPassword().equals(byUsername.getPassword())) {
                bindingResult.rejectValue("password","error.user","Username or password not matched!!");
            }
        }

        if (bindingResult.hasErrors()) {
            return "login";
        }

        assert byUsername != null;
        userSession.setId(byUsername.getId());
        userSession.setUsername(byUsername.getUsername());


        return "redirect:/";
    }



    @GetMapping("/logout")
    public String logout() {
        userSession.loqOut();
        return "redirect:/";
    }



    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("userRegDTO", new UserRegistrationDTO());
        if (userSession.isLoggedIn()) {
            return "redirect:/";
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("userRegDTO") UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (userService.findByUsername(userRegistrationDTO.getUsername()) != null) {
            bindingResult.rejectValue("username","error.user","Username already exist!!");
        }

        if (userService.findByEmail(userRegistrationDTO.getEmail()) != null) {
            bindingResult.rejectValue("email","error.user","Email already exist!!");
        }

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getPasswordConfirm())) {
            bindingResult.rejectValue("passwordConfirm","error.user","Password not matched!!");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }


        User userRegistered = User.builder()
                .username(userRegistrationDTO.getUsername())
                .email(userRegistrationDTO.getEmail())
                .password(userRegistrationDTO.getPassword())
                .build();

        userService.userRegistered(userRegistered);
        redirectAttributes.addFlashAttribute("success", "Registration successful !!!");
        return "redirect:/user/login";
    }

}
