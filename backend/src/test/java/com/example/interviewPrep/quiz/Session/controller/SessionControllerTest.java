package com.example.interviewPrep.quiz.Session.controller;

import com.example.interviewPrep.quiz.controller.SessionController;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp(){
        given(authenticationService.login()).willReturn("a.b.c");
    }

    @Test
    void login() throws Exception {
        mockMvc.perform(post("/session"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(".")));
    }

}
