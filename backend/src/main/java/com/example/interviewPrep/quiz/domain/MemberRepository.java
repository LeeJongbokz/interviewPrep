package com.example.interviewPrep.quiz.domain;

import java.util.Optional;

public interface MemberRepository {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndType(String email, String type);

    Member save(Member member);

    void delete(Member member);
}
