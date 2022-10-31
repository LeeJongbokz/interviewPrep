package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.controller.QuestionController;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.exception.QuestionNotFoundException;
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
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockCustomOAuth2Account()
@WebMvcTest(QuestionController.class)
public class QuestionDeleteWebControllerTest {

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

        Question question = Question.builder()
                .id(1L)
                .title("자바 1번문제")
                .type("자바")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        jsonRequest = objectMapper.writeValueAsString(questionDTO);

        given(questionService.deleteQuestion(eq(1000L))).willThrow(new QuestionNotFoundException(1000L));
        when(questionService.deleteQuestion(1L)).thenReturn(question);
    }


    @Test
    @DisplayName("존재하는 Question 삭제")
    void deleteQuestion() throws Exception{
        Long id = 1L;
        mockMvc.perform(delete("/question/"+id))
                .andExpect(status().isNoContent());

        verify(questionService).deleteQuestion(1L);
    }

       /*
        @Test
        void deleteQuestionWithNotExistedId() throws Exception{
            mockMvc.perform(delete("/test/delete/1000"))
                .andExpect(status().isNotFound());

        verify(questionService).deleteQuestion(1000L);
        }
        */
}
