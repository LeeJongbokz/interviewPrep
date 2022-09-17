package com.example.interviewPrep.quiz.Answer.controller;

import com.example.interviewPrep.quiz.controller.AnswerController;
import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.service.AnswerService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AnswerController.class)
public class AnswerCreateWebControllerTest {

    @MockBean
    AnswerService answerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<AnswerDTO> answerDTOs;
    String jsonRequest;

    @BeforeEach
    void setUp() throws Exception {

        answerDTOs = new ArrayList<>();

        AnswerDTO answerDTO1 = AnswerDTO.builder()
                .content("새 답안입니다1.")
                .build();


        answerDTOs.add(answerDTO1);
        jsonRequest = objectMapper.writeValueAsString(answerDTOs);

        Answer answer = Answer.builder()
                .content("새 답안입니다")
                .build();
        when(answerService.createAnswer(any(AnswerDTO.class))).thenReturn(answer);
    }



    /*
    @Test
    void create() throws Exception{

        mockMvc.perform(post("/answer")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated());

        verify(answerService).createAnswers(refEq(answerDTOs));
    }
    */



    @Test
    @DisplayName("answer valid create")
    void createValidAnswer() throws Exception{
        //when
        mockMvc.perform(post("/answer/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"새 답안입니다\"," +
                        "\"questionId\":1}")
        )
        //then
                .andDo(print())
                .andExpect(status().isCreated());

        verify(answerService).createAnswer(any(AnswerDTO.class));
    }


    @Test
    @DisplayName("answer invalid create")
    void createInvalidAnswer() throws Exception{
        //when
        mockMvc.perform(post("/answer/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"\"," +
                        "\"questionId\":0}")
        )
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

}
