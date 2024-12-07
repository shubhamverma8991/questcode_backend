package com.prepquest.prepquest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "question")
public class Question {

    @Id
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String question;
    
    private String askedInCompany;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;  // Store JSON content as a string

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAskedInCompany() {
        return askedInCompany;
    }

    public void setAskedInCompany(String askedInCompany) {
        this.askedInCompany = askedInCompany;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
