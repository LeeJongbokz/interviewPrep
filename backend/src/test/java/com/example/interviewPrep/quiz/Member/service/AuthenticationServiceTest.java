package com.example.interviewPrep.quiz.Member.service;

import com.example.interviewPrep.quiz.repository.MemberRepository;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {

    private static final String email = "hello@gmail.com";
    private static final String password = "1234";
    private static final String SECRET = "12345678123456781234567812345678";
    private AuthenticationService authenticationService;
    @Autowired
    private MemberRepository memberRepository;

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