package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByEmail(String email);

    Member save(Member member);

    void delete(Member member);
}
