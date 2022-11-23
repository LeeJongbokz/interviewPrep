package com.example.interviewPrep.quiz.exam.repository;

import com.example.interviewPrep.quiz.exam.domain.ExamAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamAnswerRepository extends JpaRepository<ExamAnswer, Long> {
}
