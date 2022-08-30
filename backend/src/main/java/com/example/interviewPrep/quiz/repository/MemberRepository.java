package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public List<Member> findByEmail(String email){
        return em.createQuery("SELECT q from Member AS q where q.email = :email", Member.class)
                .setParameter("email", email)
                .getResultList();
    }




}
