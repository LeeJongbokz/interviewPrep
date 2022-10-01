package com.example.interviewPrep.quiz.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Optional<Question> findById(Long id);
    Optional<Question> findByTitle(String title);
    List<Question> findByType(String type);
    Page<Question> findByType(String type, Pageable pageable);

    List<Question> findAll();
    Question save(Question question);
    void delete(Question question);

}
