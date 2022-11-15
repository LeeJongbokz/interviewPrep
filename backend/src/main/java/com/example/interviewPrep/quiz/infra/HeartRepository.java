package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    Heart save(Heart heart);

    Optional<Heart> findById(Long id);

    void delete(Heart heart);

    Optional<Heart> findByAnswerIdAndMemberId(Long answerId, Long memberId);

    List<Heart> findByAnswerId(Long id);

    int countHeartByAnswerId(long id);
}
