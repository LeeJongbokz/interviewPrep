package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Heart;

import java.util.List;
import java.util.Optional;

public interface HeartRepository {
    Heart save(Heart heart);

    Optional<Heart> findById(Long id);

    void delete(Heart heart);

    List<Heart> findByAnswerId(Long id);

    int countHeartByAnswerId(long id);
}
