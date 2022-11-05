package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface JpaHeartRepository extends HeartRepository, JpaRepository<Heart, Long> {
    Heart save(Heart heart);

    Optional<Heart> findById(Long id);

    Optional<Heart> findByAnswerIdAndMemberId(Long answerId, Long memberId);

    void delete(Heart heart);

    List<Heart> findByAnswerId(Long id);

    int countHeartByAnswerId(long id);
}
