package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.Language;
import com.dictionaryapp.repo.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    public List<Language> findAll() {
        return languageRepository.findAll();
    }
}
