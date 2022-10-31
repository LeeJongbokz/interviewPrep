package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public interface JpaHeartRepository extends HeartRepository, JpaRepository<Heart, Long> {
    Heart save(Heart heart);

    Optional<Heart> findById(Long id);

    void delete(Heart heart);

    List<Heart> findByAnswerId(Long id);
    int countHeartByAnswerId(long id);
}
