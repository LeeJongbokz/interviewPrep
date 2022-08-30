package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.controller.QuestionController;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.exception.QuestionNotFoundException;
import com.example.interviewPrep.quiz.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(QuestionController.class)
public class QuestionUpdateWebControllerTest {

    @MockBean
    QuestionService questionService;

    @Autowired
    MockMvc mockMvc;

    QuestionDTO questionDTO;
    QuestionDTO updatedQuestionDTO;
    String jsonRequest;
    String updatedJsonRequest;

    @BeforeEach
    void setUp() throws Exception{

        questionDTO = QuestionDTO.builder()
                .id(1L)
                .title("problem1")
                .type("java")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        jsonRequest = objectMapper.writeValueAsString(questionDTO);

        updatedQuestionDTO = QuestionDTO.builder()
                .id(1L)
                .title("problem2")
                .type("java")
                .build();

        updatedJsonRequest = objectMapper.writeValueAsString(updatedQuestionDTO);

        given(questionService.updateQuestion(eq(1L), any(QuestionDTO.class)))
                .will(invocation -> {
                    Long id = invocation.getArgument(0);
                    QuestionDTO questionDTO = invocation.getArgument(1);
                    return Question.builder()
                            .id(id)
                            .title(questionDTO.getTitle())
                            .type(questionDTO.getType())
                            .build();
                });

        given(questionService.updateQuestion(eq(1000L), any(QuestionDTO.class)))
                .willThrow(new QuestionNotFoundException(1000L));
    }


    @Test
    @DisplayName("존재하는 Question 업데이트를 위한 PUT 요청")
    void updateWithExistedQuestion() throws Exception{
        mockMvc.perform(put("/test/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedJsonRequest)
                )
                .andExpect(status().isOk());

        verify(questionService).updateQuestion(eq(1L), any(QuestionDTO.class));
    }

       /*
       @Test
       @DisplayName("존재하지 않는 Question에 대한 업데이트를 위한 PUT 요청")
       void updateWithNotExistedQuestion() throws Exception{
              mockMvc.perform(put("/test/update")
                                     .contentType(MediaType.APPLICATION_JSON)
                                     .content(updatedJsonRequest)
                             )
                      .andExpect(status().isNotFound());

              verify(questionService).updateQuestion(eq(1000L), any(QuestionDTO.class));
       }
       */
}
