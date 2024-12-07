package com.prepquest.prepquest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepquest.prepquest.model.Language;
import com.prepquest.prepquest.repository.LanguageRepository;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    @PostMapping
    public Language createLanguage(@RequestBody Language language) {
        Language savedLanguage = languageRepository.save(language);
        createQuestionsTableForLanguage(savedLanguage.getLanguageName());
        return savedLanguage;
    }

    private void createQuestionsTableForLanguage(String languageName) {
        String tableName = languageName.toLowerCase(); // Use lowercase for table name
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "id BIGINT AUTO_INCREMENT PRIMARY KEY, "
                + "question TEXT, "
                + "askedInCompany VARCHAR(255), "
                + "content TEXT, "
                + "language_id BIGINT NOT NULL, "
                + // Foreign key reference to Language
                "FOREIGN KEY (language_id) REFERENCES language(id)"
                + // Assuming 'language' is the table name for Language
                ")";
        jdbcTemplate.execute(sql);
    }
}
