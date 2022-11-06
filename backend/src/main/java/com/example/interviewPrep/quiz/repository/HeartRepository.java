package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Heart;

import java.util.List;

public interface HeartRepository {
    List<Heart> findByAnswerId(Long id);
    int countHeartByAnswerId(long id);
}
