package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.question.QuestionController;
import com.example.interviewPrep.quiz.question.Question;
import com.example.interviewPrep.quiz.question.QuestionDTO;
import com.example.interviewPrep.quiz.question.QuestionNotFoundException;
import com.example.interviewPrep.quiz.security.WithMockCustomOAuth2Account;
import com.example.interviewPrep.quiz.member.CustomOAuth2UserService;
import com.example.interviewPrep.quiz.question.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

        when(questionService.deleteQuestion(1L)).thenReturn(question);
        when(questionService.deleteQuestion(1000L)).thenThrow(new QuestionNotFoundException(1000L));
    }


    @Test
    @DisplayName("valid Question delete")
    void deleteQuestion() throws Exception{
        Long id = 1L;
        mockMvc.perform(delete("/question/"+id))
                .andDo(print())
                .andExpect(status().isOk());

        verify(questionService).deleteQuestion(1L);
    }


    @Test
    @DisplayName("Invalid Question delete")
    void deleteQuestionWithNotExistedId() throws Exception{
            mockMvc.perform(delete("/question/"+1000L))
                    .andDo(print())
                .andExpect(status().isNoContent());

        verify(questionService).deleteQuestion(1000L);
        }

}
