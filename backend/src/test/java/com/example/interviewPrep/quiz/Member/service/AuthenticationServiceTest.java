package com.example.interviewPrep.quiz.Member.service;

import com.example.interviewPrep.quiz.domain.MemberRepository;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class AuthenticationServiceTest {

    private static final String email = "hello@gmail.com";
    private static final String password = "1234";
    private static final String SECRET = "12345678123456781234567812345678";
    private AuthenticationService authenticationService;
    @Autowired
    private final MemberRepository memberRepository = mock(MemberRepository.class);

    @BeforeEach
    void setUp(){
        JwtUtil jwtUtil = new JwtUtil(SECRET);
        authenticationService = new AuthenticationService(jwtUtil, memberRepository);

    }


    @Test
    void login(){
        String accessToken = authenticationService.login(email, password);

        assertThat(accessToken).contains(".");
    }
}