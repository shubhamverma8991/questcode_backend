package com.prepquest.prepquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prepquest.prepquest.model.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

    public Language findByLanguageName(String languageName);

}
