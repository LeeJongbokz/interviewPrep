package com.example.interviewPrep.quiz.Member.repository;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.infra.JpaMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired private JpaMemberRepository memberRepository;

    Member member;
    String email;
    String password;
    String type;

    @BeforeEach
    void setUp(){



        // Given
        email = "hello@gmail.com";
        password = "1234";
        type = "google";

        member = Member.builder()
                .id(3L)
                .email(email)
                .password(password)
                .type(type)
                .build();

    }


    @Test
    @DisplayName("회원을 DB에 저장")
    public void save(){

        // When
        Member savedMember = memberRepository.save(member);
        System.out.println("savedMember는?" + savedMember);

        // Then
        String savedEmail = member.getEmail();

        assertEquals(member, memberRepository.findByEmail(savedEmail).get());
        assertEquals(member, memberRepository.findByEmailAndType(savedEmail,type).get());
    }
    
    @Test
    @DisplayName("Email로 회원 찾기")
    public void findByEmail(){

        // When
        memberRepository.save(member);
        Optional<Member> searchedMember = memberRepository.findByEmail(email);

        // Then
        assertThat(email).isEqualTo(searchedMember.get().getEmail());
    }

     


}
