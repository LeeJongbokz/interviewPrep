package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class AnswerRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Answer answer){
        em.persist(answer);
    }

    public void save(List<Answer> answers){
        for(Answer answer: answers){
            em.persist(answer);
        }
    }
    public Answer findById(Long id){
        return em.find(Answer.class, id);
    }

}
