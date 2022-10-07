package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.HeartRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaHeartRepository extends HeartRepository, JpaRepository<Heart, Long> {
    Heart save(Heart heart);
    Optional<Heart> findById(Long id);
    void delete(Heart heart);
}
