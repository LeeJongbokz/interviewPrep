package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart save(Heart heart);

    Optional<Heart> findById(Long id);

    void delete(Heart heart);
}
