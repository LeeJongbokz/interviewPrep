package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionJpaRepository extends JpaRepository<Question, Long>{

    Page<Question> findByType(String type, Pageable pageable);
}
