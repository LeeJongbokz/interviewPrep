package com.example.interviewPrep.quiz.repository;

import com.example.interviewPrep.quiz.domain.Member;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndType(String email, String type);

    Optional<Member> findById(Long id);

    Member save(Member member);

    void delete(Member member);
}
