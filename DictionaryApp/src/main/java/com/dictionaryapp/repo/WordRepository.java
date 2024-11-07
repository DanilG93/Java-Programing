package com.dictionaryapp.repo;

import com.dictionaryapp.model.entity.LanguageName;
import com.dictionaryapp.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word,Long> {
        List<Word> findAllByLanguageName(LanguageName name);

}
