package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface JpaAnswerRepository extends AnswerRepository, JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long id);

    Answer save(Answer answer);

    void delete(Answer answer);

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Answer s where s.id = :id")
    Optional<Answer> findByIdWithOptimisticLock(@Param("id") Long id);

    Answer saveAndFlush(Answer answer);
}
