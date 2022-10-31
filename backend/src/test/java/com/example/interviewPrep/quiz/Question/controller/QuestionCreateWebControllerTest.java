package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.controller.QuestionController;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.security.WithMockCustomOAuth2Account;
import com.example.interviewPrep.quiz.service.CustomOAuth2UserService;
import com.example.interviewPrep.quiz.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockCustomOAuth2Account()
@WebMvcTest(QuestionController.class)
@WithMockCustomOAuth2Account()
public class QuestionCreateWebControllerTest {

    @MockBean
    QuestionService questionService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    MockMvc mockMvc;

    QuestionDTO questionDTO;
    String jsonRequest;

    @BeforeEach
    void setUp() throws Exception{

        questionDTO = QuestionDTO.builder()
                .id(1L)
                .title("problem1")
                .type("java")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        jsonRequest = objectMapper.writeValueAsString(questionDTO);
    }


    @Test
    @DisplayName("Question 생성")
    void createWithValidAttributes() throws Exception{
        mockMvc.perform(post("/question")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                "{\"title\":\"problem1\",\"type\":\"java\"," +
                                        "\"id\":1L}"
                        )
                )
                .andDo(print())
                .andExpect(status().isCreated());

        verify(questionService).createQuestion(any(QuestionDTO.class));
    }

}
