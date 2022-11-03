package com.example.interviewPrep.quiz.domain;

import java.util.Optional;

public interface AnswerRepository {

    Answer save(Answer answer);
    Optional<Answer> findById(Long id);
    void delete(Answer answer);
}
