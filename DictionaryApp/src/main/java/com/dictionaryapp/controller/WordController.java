package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.DTO.WordDTO;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.LanguageService;
import com.dictionaryapp.service.UserService;
import com.dictionaryapp.service.WordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/word")
public class WordController {
    @Autowired
    private UserSession userSession;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private WordService wordService;
    @Autowired
    private UserService userService;

    @GetMapping("/add")
    public String getAddWordPage(Model model) {
        model.addAttribute("wordDTO", new WordDTO());
        List<String> list = languageService.findAll().stream().map(language -> language.getName().name()).toList();
        model.addAttribute("languages", languageService.findAll());

        if (!userSession.isLoggedIn()) {
            return "redirect:/";
        }

        return "word-add";
    }

    @PostMapping("/add")
    public String addWord(@Valid @ModelAttribute("wordDTO") WordDTO wordDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("languages", languageService.findAll());
            return "word-add";
        }

        Word word = Word.builder()
                .term(wordDTO.getTerm())
                .translation(wordDTO.getTranslation())
                .example(wordDTO.getExample())
                .inputDate(wordDTO.getInputDate())
                .language(wordDTO.getLanguage())
                .addedBy(userService.findByUsername(userSession.getUsername()))
                .build();

        wordService.save(word);

        return "redirect:/";
    }
}
