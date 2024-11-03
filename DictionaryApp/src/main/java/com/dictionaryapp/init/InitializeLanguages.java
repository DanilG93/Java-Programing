package com.dictionaryapp.init;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.repo.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@AllArgsConstructor
@Component
public class InitializeLanguages implements CommandLineRunner {

    private LanguageRepository languageRepository;
    private List<Language> languages;

    @Override
    public void run(String... args) {
        Language italian = Language.builder()
                .name(LanguageName.ITALIAN)
                .description(LanguageName.ITALIAN.getDescription())
                .build();
        Language german = Language.builder()
                .name(LanguageName.GERMAN)
                .description(LanguageName.GERMAN.getDescription())
                .build();
        Language spanish = Language.builder()
                .name(LanguageName.SPANISH)
                .description(LanguageName.SPANISH.getDescription())
                .build();
        Language french = Language.builder()
                .name(LanguageName.FRENCH)
                .description(LanguageName.FRENCH.getDescription())
                .build();


        if (languageRepository.count() == 0) {
            languages.add(french);
            languages.add(spanish);
            languages.add(german);
            languages.add(italian);
            languageRepository.saveAll(languages);
        }
    }
}
