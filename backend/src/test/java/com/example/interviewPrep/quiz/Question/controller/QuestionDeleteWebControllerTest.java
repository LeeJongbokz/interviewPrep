package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.controller.QuestionController;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.exception.QuestionNotFoundException;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
public class QuestionDeleteWebControllerTest {

    @MockBean
    QuestionService questionService;

    @MockBean
    QuestionRepository questionRepository;

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

        given(questionService.deleteQuestion(eq(1000L)))
                .willThrow(new QuestionNotFoundException(1000L));
    }


    @Test
    @DisplayName("존재하는 Question 삭제")
    void deleteQuestion() throws Exception{
        mockMvc.perform(delete("/question"))
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
