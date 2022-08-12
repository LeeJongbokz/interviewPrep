package com.example.interviewPrep.quiz.repository;


import com.example.interviewPrep.quiz.domain.Question;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class QuestionRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Question question){
        em.persist(question);
    }
    public Question update(Question question){
        return em.merge(question);
    }

    public void delete(Question question){
        em.remove(question);
    }

    public Question findById(Long id){
        return em.find(Question.class, id);
    }

    public List<Question> findByTitle(String title){
        return em.createQuery("SELECT q from Question AS q where q.title = :title", Question.class)
                .setParameter("title", title)
                .getResultList();
    }


    public List<Question> findByType(String type){
        return em.createQuery("SELECT q from Question AS q where q.type = :type", Question.class)
                .setParameter("type", type)
                .getResultList();
    }

}
