package com.dictionaryapp.service;

import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.model.entity.Word;
import com.dictionaryapp.repo.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    @Autowired
    WordRepository wordRepository;

    public void save(Word word) {
        wordRepository.save(word);
    }

    public List<Word> findByLanguageName(String languageName) {
        LanguageName languageName1 = LanguageName.valueOf(languageName);
        return wordRepository.findAllByLanguageName(languageName1);
    }

    public void removeAll() {
          wordRepository.deleteAll();
    }

    public void removeById(Long id) {
        wordRepository.deleteById(id);
    }

}
