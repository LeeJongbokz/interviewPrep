package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndType(String email, String type);

    Member save(Member member);

    void delete(Member member);
}
