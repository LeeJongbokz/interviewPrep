package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long id);
    Answer save(Answer answer);
    void delete(Answer answer);
}
