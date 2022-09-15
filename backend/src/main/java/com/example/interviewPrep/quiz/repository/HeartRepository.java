package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Heart;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@Transactional
public class HeartRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Heart heart) {
        em.persist(heart);
    }

    public Optional<Heart> findById(Long id) {
        return Optional.ofNullable(em.find(Heart.class, id));
    }
}
