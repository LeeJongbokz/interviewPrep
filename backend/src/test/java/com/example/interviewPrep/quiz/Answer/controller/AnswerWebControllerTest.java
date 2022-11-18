package com.example.interviewPrep.quiz.Answer.controller;

import com.example.interviewPrep.quiz.answer.AnswerController;
import com.example.interviewPrep.quiz.answer.Answer;
import com.example.interviewPrep.quiz.question.Question;
import com.example.interviewPrep.quiz.answer.AnswerDTO;
import com.example.interviewPrep.quiz.dto.SolutionDTO;
import com.example.interviewPrep.quiz.security.WithMockCustomOAuth2Account;
import com.example.interviewPrep.quiz.answer.AnswerService;
import com.example.interviewPrep.quiz.member.CustomOAuth2UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AnswerController.class)
@WithMockCustomOAuth2Account()
public class AnswerWebControllerTest {

    @MockBean
    AnswerService answerService;

    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    List<AnswerDTO> answerDTOs;

    AnswerDTO answerDTO1;
    String jsonRequest;
    Pageable pageable;


    @BeforeEach
    void setUp() throws Exception {

        answerDTOs = new ArrayList<>();

        answerDTO1 = AnswerDTO.builder()
                .content("new answer")
                .questionId(2L)
                .id(1L)
                .build();


        answerDTOs.add(answerDTO1);
        jsonRequest = objectMapper.writeValueAsString(answerDTOs);

        Question question = Question.builder()
                .id(2L)
                .title("자바 1번문제")
                .type("자바")
                .build();

        Answer answer = Answer.builder()
                .content("new answer")
                .question(question)
                .id(1L)
                .build();

        when(answerService.createAnswer(any(AnswerDTO.class))).thenReturn(answer);
        when(answerService.readAnswer(answerDTO1)).thenReturn(answerDTO1);
        when(answerService.deleteAnswer(answerDTO1)).thenReturn(answer);

        List<SolutionDTO> solutionDTOs= new ArrayList<>();
        SolutionDTO solutionDTO;

        for(int i = 1; i<11; i++) {
            solutionDTO = SolutionDTO.builder()
                    .answerId(1L)
                    .answer("answer"+i)
                    .heartCnt(i)
                    .name("jin"+i)
                    .build();
            solutionDTOs.add(solutionDTO);
        }

        pageable = PageRequest.of(0, 10);
        Page<SolutionDTO> solutions = new PageImpl<>(solutionDTOs);

        when(answerService.getSolution(1L,"all", pageable)).thenReturn(solutions);

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
        mockMvc.perform(post("/answer")
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
        mockMvc.perform(post("/answer")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"content\":\"\"," +
                        "\"questionId\":0}")
        )
                //then
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }


    @Test
    @DisplayName("answer valid read")
    void readValidAnswer() throws Exception{
        //given
        Long id = 1L;

        //when
        mockMvc.perform(get("/answer/"+id))
                //then
                .andDo(print())
                .andExpect(status().isOk());

        verify(answerService).readAnswer(answerDTO1);
    }


    @Test
    @DisplayName("answer Invalid read")
    void readInValidAnswer() throws Exception{
        //given
        Long id = 2L;

        //when
        mockMvc.perform(get("/answer/"+id))
                //then
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(answerService).readAnswer(answerDTO1);
    }



    @Test
    @DisplayName("answer valid delete")
    void deleteValidAnswer() throws Exception{
        //given
        Long id = 1L;

        //when
        mockMvc.perform(delete("/answer/"+id))
                //then
                .andDo(print())
                .andExpect(status().isOk());

        verify(answerService).deleteAnswer(answerDTO1);
    }


    @Test
    @DisplayName("answer Invalid delete")
    void deleteInValidAnswer() throws Exception{
        //given
        Long id = 2L;

        //when
        mockMvc.perform(delete("/answer/"+id))
                //then
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(answerService).deleteAnswer(answerDTO1);
    }



    @Test
    @DisplayName("Answer valid id, type")
    void findSolution() throws Exception{
        //given
        Long id =1L;
        String type ="all";

        //when
        mockMvc.perform(get("/answer/solution/"+id+"/"+type)
                .param("page", "0"))

                //then
                .andDo(print())
                .andExpect(status().isOk());

        verify(answerService).getSolution(id, type, pageable);
    }


}
