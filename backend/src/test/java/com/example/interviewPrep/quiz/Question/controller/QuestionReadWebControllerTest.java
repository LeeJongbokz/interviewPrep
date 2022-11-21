package com.example.interviewPrep.quiz.Question.controller;

import com.example.interviewPrep.quiz.question.QuestionController;
import com.example.interviewPrep.quiz.question.Question;
import com.example.interviewPrep.quiz.dto.FilterDTO;
import com.example.interviewPrep.quiz.question.QuestionDTO;
import com.example.interviewPrep.quiz.security.WithMockCustomOAuth2Account;
import com.example.interviewPrep.quiz.member.CustomOAuth2UserService;
import com.example.interviewPrep.quiz.question.QuestionService;
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
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockCustomOAuth2Account()
@WebMvcTest(QuestionController.class)
public class QuestionReadWebControllerTest {

    @MockBean
    QuestionService questionService;


    @MockBean
    CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    MockMvc mockMvc;

    Question question;
    QuestionDTO questionDTO;
    List<QuestionDTO> questionDTOs;
    Pageable pageable;

    @BeforeEach
    void setUp() throws Exception{

        question = Question.builder()
                .title("자바 1번문제")
                .type("자바")
                .build();

        questionDTOs = new ArrayList<>();

        for(int i = 1; i<11; i++) {
            questionDTO = QuestionDTO.builder()
                    .title("problem1")
                    .id(Long.valueOf(i))
                    .type("java")
                    .build();
            questionDTOs.add(questionDTO);
        }

        when(questionService.findQuestion(10L)).thenReturn(question);

        pageable = PageRequest.of(0, 10);
        Page<QuestionDTO> questions = new PageImpl<>(questionDTOs);

        when(questionService.findByType("java", pageable)).thenReturn(Optional.of(questions));
        when(questionService.getQuestion(10L)).thenReturn(questionDTO);

        ArrayList<FilterDTO> lang = new ArrayList<>();

        FilterDTO fd = FilterDTO.builder()
                .language("java")
                .build();

        lang.add(fd);

        fd = FilterDTO.builder()
                .language("os")
                .build();

        lang.add(fd);

        when(questionService.findFilterLanguage()).thenReturn(lang);


    }


    @Test
    @DisplayName("Question valid type inquiry")
    void findByValidType() throws Exception{
        //given
        String type ="java";

        //when
        mockMvc.perform(get("/question/"+type)
                        .param("page", "0"))

                //then
                .andDo(print())
                .andExpect(status().isOk());

        verify(questionService).findByType(type, pageable);
    }


    @Test
    @DisplayName("Question invalid type inquiry")
    void findByInvalidType() throws Exception{
        //given
        String type ="c++";

        //when
        mockMvc.perform(get("/question/"+type)
                        .param("page", "0"))

                //then
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(questionService).findByType(type, pageable);
    }



    @Test
    @DisplayName("Question valid id inquiry")
    void findByValidSingleId() throws Exception{
        //given
        Long id = 10L;

        //when
        mockMvc.perform(get("/question/single/"+id))

                //then
                .andDo(print())
                .andExpect(status().isOk());


    }


    @Test
    @DisplayName("Question invalid id inquiry")
    void findByInvalidSingleId() throws Exception{
        //given
        Long id = 11L;

        //when
        mockMvc.perform(get("/question/single/"+id))

                //then
                .andDo(print())
                .andExpect(status().isNotFound());


    }


    @Test
    @DisplayName("Question filter inquiry")
    void findByFilterLanguage() throws Exception{

        //when
        mockMvc.perform(get("/question/filter"))

                //then
                .andDo(print())
                .andExpect(status().isOk());

    }

}