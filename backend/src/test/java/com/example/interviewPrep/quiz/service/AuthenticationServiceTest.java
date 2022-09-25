package com.example.interviewPrep.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp(){

        authenticationService = new AuthenticationService();

    }


    @Test
    void login(){
        String accessToken = authenticationService.login();

        assertThat(accessToken).contains(".");
    }
}