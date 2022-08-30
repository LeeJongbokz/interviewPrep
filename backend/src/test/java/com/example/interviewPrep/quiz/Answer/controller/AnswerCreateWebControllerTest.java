package com.example.interviewPrep.quiz.Answer.controller;

import com.example.interviewPrep.quiz.controller.AnswerController;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.service.AnswerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnswerController.class)
public class AnswerCreateWebControllerTest {


    @MockBean
    AnswerService answerService;

    @Autowired
    MockMvc mockMvc;

    @Mock
    List<AnswerDTO> mockDTOs;

    List<AnswerDTO> answerDTOs;
    String jsonRequest;

    @BeforeEach
    void setUp() throws Exception{

        answerDTOs = new ArrayList<>();

        AnswerDTO answerDTO1 = AnswerDTO.builder()
                              .content("새 답안입니다1.")
                              .build();

        AnswerDTO answerDTO2 = AnswerDTO.builder()
                              .content("새 답안입니다2.")
                              .build();

        answerDTOs.add(answerDTO1);
        answerDTOs.add(answerDTO2);

        ObjectMapper objectMapper = new ObjectMapper();

        jsonRequest = objectMapper.writeValueAsString(answerDTOs);
    }


    @Test
    void create() throws Exception{

        mockMvc.perform(post("/test/answer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest)
                )
                .andExpect(status().isCreated());

        verify(answerService).createAnswers(refEq(answerDTOs));
    }



}
