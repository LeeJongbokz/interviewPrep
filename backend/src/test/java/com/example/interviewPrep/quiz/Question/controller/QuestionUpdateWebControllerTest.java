package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.controller.QuestionController;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.exception.QuestionNotFoundException;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import com.example.interviewPrep.quiz.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@AutoConfigureMockMvc
@WebMvcTest(QuestionController.class)
public class QuestionUpdateWebControllerTest {

    @MockBean
    QuestionService questionService;
    @MockBean
    QuestionRepository questionRepository;
    @Autowired
    MockMvc mockMvc;

    QuestionDTO validUpdateQuestionDTO;
    QuestionDTO InvalidUpdateQuestionDTO;

    String validUpdateJsonRequest;
    String InvalidUpdateJsonRequest;

    @BeforeEach
    void setUp() throws Exception{

        questionService = new QuestionService(questionRepository);

        Question question = Question.builder()
                .id(1L)
                .title("자바 1번 문제")
                .type("java")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();

        given(questionService.getQuestion(1L)).willReturn(question);

        given(questionService.getQuestion(1000L))
                .willThrow(new QuestionNotFoundException(1000L));

        validUpdateQuestionDTO = QuestionDTO.builder()
                .id(1L)
                .title("problem2")
                .type("java")
                .build();

        validUpdateJsonRequest = objectMapper.writeValueAsString(validUpdateQuestionDTO);

        InvalidUpdateQuestionDTO = QuestionDTO.builder()
                .id(1000L)
                .title("problem2")
                .type("java")
                .build();

        InvalidUpdateJsonRequest = objectMapper.writeValueAsString(InvalidUpdateQuestionDTO);


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
        mockMvc.perform(put("/question/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validUpdateJsonRequest)
                )
                .andExpect(status().isOk());

        verify(questionService).updateQuestion(eq(1L), any(QuestionDTO.class));
    }


    @Test
    @DisplayName("존재하지 않는 Question에 대한 업데이트를 위한 PUT 요청")
    void updateWithNotExistedQuestion() throws Exception{
        mockMvc.perform(put("/question/1000")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"id\":1000L," +
                                        "\"title\": \"problem2\"," +
                                        "\"type\":\"java\"}")
                        )
                      .andExpect(status().is4xxClientError());

       verify(questionService).updateQuestion(eq(1000L), any(QuestionDTO.class));
    }
}
