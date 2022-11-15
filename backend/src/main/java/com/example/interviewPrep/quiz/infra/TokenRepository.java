package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    boolean existsByRefreshToken(String token);
}