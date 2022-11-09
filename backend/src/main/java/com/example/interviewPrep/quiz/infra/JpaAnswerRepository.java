package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@Primary
public interface JpaAnswerRepository extends AnswerRepository, JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long id);
    Answer save(Answer answer);
    void delete(Answer answer);

    @Query("SELECT a FROM Answer a, Member m " +
            "where a.question.id = ?1 and a.member.id = m.id ORDER BY a.heartCnt desc")
    Page<Answer> findSolution(Long id, Pageable pageable);
}
