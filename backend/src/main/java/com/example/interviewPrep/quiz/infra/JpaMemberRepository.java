package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.MemberRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Primary
public interface JpaMemberRepository extends MemberRepository, JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndType(String email, String type);

    Member save(Member member);

    void delete(Member member);
}
