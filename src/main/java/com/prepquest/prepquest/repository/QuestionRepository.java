package com.prepquest.prepquest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prepquest.prepquest.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
