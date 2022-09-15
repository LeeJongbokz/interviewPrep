package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long>{

    Optional<Question> findByTitle(String title);
    List<Question> findByType(String type);
    Page<Question> findByType(String type, Pageable pageable);
    Question save(Question question);
    void delete(Question question);
}
