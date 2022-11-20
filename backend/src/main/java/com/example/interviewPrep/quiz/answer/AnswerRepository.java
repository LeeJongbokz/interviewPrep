package com.example.interviewPrep.quiz.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findById(Long id);

    Answer save(Answer answer);

    Answer saveAndFlush(Answer answer);

    void delete(Answer answer);


    @Query("SELECT a FROM Answer a, Member m " +
            "where a.question.id = ?1 and a.member.id = m.id ORDER BY a.heartCnt desc")
    Page<Answer> findSolution(Long id, Pageable pageable);

    @Transactional
    @Lock(value = LockModeType.OPTIMISTIC)
    @Query("select s from Answer s where s.id = :id")
    Optional<Answer> findByIdWithOptimisticLock(@Param("id") Long id);
}
