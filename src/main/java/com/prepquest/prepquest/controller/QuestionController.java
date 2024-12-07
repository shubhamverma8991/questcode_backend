package com.prepquest.prepquest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prepquest.prepquest.model.Language;
import com.prepquest.prepquest.model.Question;
import com.prepquest.prepquest.repository.LanguageRepository;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @SuppressWarnings("unused")
    private LanguageRepository languageRepository;

    // Get all questions for a specific language
    @GetMapping("/{languageName}")
    public List<Map<String, Object>> getAllQuestionsByLanguage(@PathVariable String languageName) {
        String tableName = languageName.toLowerCase();
        String sql = "SELECT * FROM " + tableName;
        return jdbcTemplate.queryForList(sql);
    }

    // Get a specific question by ID for a specific language
    @GetMapping("/{languageName}/{id}")
    public Map<String, Object> getQuestionById(@PathVariable String languageName, @PathVariable Long id) {
        String tableName = languageName.toLowerCase();
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    // Create a new question for a specific language
    @PostMapping("/{languageName}")
    @SuppressWarnings("CallToPrintStackTrace")
    public void createQuestion(@PathVariable String languageName, @RequestBody Question question) {
        try {
            Language language = languageRepository.findByLanguageName(languageName);
            if (language == null) {
                throw new RuntimeException("Language not found: " + languageName);
            }
            question.setLanguage(language); // Set the Language object

            String tableName = languageName.toLowerCase();
            String sql = "INSERT INTO " + tableName + " (question, askedInCompany, content, language_id) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(sql, question.getQuestion(), question.getAskedInCompany(), question.getContent(), language.getId());
        } catch (RuntimeException e) {
            e.printStackTrace(); // Log the error for debugging
            throw e; // Rethrow or handle accordingly
        }
    }

    // Update a specific question by ID for a specific language
    @PutMapping("/{languageName}/{id}")
    public void updateQuestion(@PathVariable String languageName, @PathVariable Long id, @RequestBody Question questionDetails) {
        String tableName = languageName.toLowerCase();
        String sql = "UPDATE " + tableName + " SET question = ?, askedInCompany = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, questionDetails.getQuestion(), questionDetails.getAskedInCompany(), questionDetails.getContent(), id);
    }

    // Delete a specific question by ID for a specific language
    @DeleteMapping("/{languageName}/{id}")
    public void deleteQuestion(@PathVariable String languageName, @PathVariable Long id) {
        String tableName = languageName.toLowerCase();
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
