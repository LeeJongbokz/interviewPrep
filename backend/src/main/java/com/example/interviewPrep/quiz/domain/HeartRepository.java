package com.example.interviewPrep.quiz.domain;

import java.util.Optional;

public interface HeartRepository {
    Heart save(Heart heart);

    Optional<Heart> findById(Long id);

    void delete(Heart heart);
}
