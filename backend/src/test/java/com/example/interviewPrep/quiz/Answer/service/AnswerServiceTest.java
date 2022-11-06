package com.example.interviewPrep.quiz.Answer.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.AnswerRepository;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.domain.QuestionRepository;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.infra.JpaAnswerRepository;
import com.example.interviewPrep.quiz.infra.JpaQuestionRepository;
import com.example.interviewPrep.quiz.service.AnswerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class AnswerServiceTest {

    private AnswerService answerService;

    private final AnswerRepository answerRepository = mock(JpaAnswerRepository .class);
    private final QuestionRepository questionRepository =  mock(JpaQuestionRepository.class);


    Answer answer1;
    Answer answer2;
    List<Answer> answers;
    AnswerDTO answerDTO1;
    AnswerDTO answerDTO2;
    List<AnswerDTO> answerDTOs;

    Question question;


    @BeforeEach
    public void setUp(){

        answerService = new AnswerService(answerRepository, questionRepository);

        answerDTOs = new ArrayList<>();

        answerDTO1 = AnswerDTO.builder()
                .content("새 답안입니다.")
                .questionId(1L)
                .build();

        answerDTO2 = AnswerDTO.builder()
                .content("새 답안입니다2.")
                .build();

        answerDTOs.add(answerDTO1);
        answerDTOs.add(answerDTO2);

        question = Question.builder()
                .id(1L)
                .build();

        when(questionRepository.findById(1L)).thenReturn(Optional.ofNullable(question));
    }


    @Test
    public void createAnswers(){

        answers = new ArrayList<>();

        answer1 = Answer.builder()
                .content(answerDTO1.getContent())
                .build();

        answer2 = Answer.builder()
                .content(answerDTO2.getContent())
                .build();

        answers.add(answer1);
        answers.add(answer2);

        int order = 0;
        for(Answer answer: answers){
            assertThat(answer.getContent()).isEqualTo(answerDTOs.get(order++).getContent());
        }
    }


    @Test
    @DisplayName("single answer create")
    public void createAnswer(){

        //given
        answer1 = Answer.builder()
                .content(answerDTO1.getContent())
                .build();

        //when
        Answer myAns = answerService.createAnswer(answerDTO1);

        //then
        assertThat(myAns.getContent()).isEqualTo(answer1.getContent());

    }



}
