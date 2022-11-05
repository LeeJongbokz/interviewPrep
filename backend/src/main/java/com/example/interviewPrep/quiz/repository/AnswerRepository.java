package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AnswerRepository {

    Answer save(Answer answer);

    Answer saveAndFlush(Answer answer);

    Optional<Answer> findById(Long id);

    void delete(Answer answer);

    Optional<Answer> findByIdWithOptimisticLock(@Param("id") Long id);
}
