package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.AnswerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@Primary
public interface JpaAnswerRepository extends AnswerRepository, JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long id);
    Answer save(Answer answer);
    void delete(Answer answer);
}
