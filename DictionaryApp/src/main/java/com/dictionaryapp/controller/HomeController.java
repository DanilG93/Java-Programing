package com.dictionaryapp.controller;

import com.dictionaryapp.config.UserSession;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.service.WordService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class HomeController {

    private UserSession userSession;
    private WordService wordService;

    @GetMapping("/")
    public String getIndexPage(Model model) {

        if (userSession.isLoggedIn()) {

            List<Word> frenchWords = wordService.findByLanguageName("FRENCH");
            List<Word> spanishWords= wordService.findByLanguageName("SPANISH");
            List<Word> germanWords = wordService.findByLanguageName("GERMAN");
            List<Word> italianWords = wordService.findByLanguageName("ITALIAN");
            int count = frenchWords.size() + spanishWords.size() + germanWords.size() + italianWords.size();

            model.addAttribute("frenchWords",frenchWords);
            model.addAttribute("spanishWords",spanishWords);
            model.addAttribute("germanWords",germanWords);
            model.addAttribute("italianWords",italianWords);
            model.addAttribute("allWordsCount", count);
            return allWords();
        }

        return "index";
    }


    @GetMapping("/home")
    public String allWords() {
        return "home";
    }

    @PostMapping("/home/remove-all")
    public String removeAll() {

        wordService.removeAll();
        return "redirect:/";
    }

    @PostMapping("/home/remove/{id}")
    private String removeById(@PathVariable Long id) {
        wordService.removeById(id);
        return "redirect:/";
    }
}
