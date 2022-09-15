package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
}
