package com.example.interviewPrep.quiz.Answer.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswerServiceTest {

    @Autowired
    AnswerRepository answerRepository;

    Answer answer1;
    Answer answer2;
    List<Answer> answers;
    AnswerDTO answerDTO1;
    AnswerDTO answerDTO2;
    List<AnswerDTO> answerDTOs;

    @BeforeEach
    public void setUp(){

        answerDTOs = new ArrayList<>();

        answerDTO1 = AnswerDTO.builder()
                .content("새 답안입니다.")
                .build();

        answerDTO2 = AnswerDTO.builder()
                .content("새 답안입니다2.")
                .build();

        answerDTOs.add(answerDTO1);
        answerDTOs.add(answerDTO2);
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



}
