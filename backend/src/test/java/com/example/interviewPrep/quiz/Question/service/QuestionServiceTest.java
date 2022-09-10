package com.example.interviewPrep.quiz.Question.service;

import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.repository.QuestionJpaRepository;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import com.example.interviewPrep.quiz.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class QuestionServiceTest {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    QuestionJpaRepository questionJpaRepository;

    private Question question;
    private QuestionDTO questionDTO;
    private QuestionDTO updatedQuestionDTO;
    private List<QuestionDTO> questionDTOS;

    @BeforeEach
    void setUp(){
        questionDTO = QuestionDTO.builder()
                                .title("자바 1번 문제")
                                .type("java")
                                .build();


        updatedQuestionDTO = QuestionDTO.builder()
                            .title("자바 1번 문제 수정")
                            .type("java")
                            .build();

    }

    @Test
    @DisplayName("새 Question 생성")
    void createQuestion() {

        question = Question.builder()
                .title(questionDTO.getTitle())
                .type(questionDTO.getType())
                .build();

        assertThat(question.getTitle()).isEqualTo(questionDTO.getTitle());
        assertThat(question.getType()).isEqualTo(questionDTO.getType());
    }

    @Test
    @DisplayName("Question 업데이트")
    void updateQuestion(){

       Question question = questionService.createQuestion(questionDTO);

       Question updatedQuestion = questionService.updateQuestion(question.getId(), updatedQuestionDTO);

       assertThat(updatedQuestion.getTitle()).isEqualTo(updatedQuestionDTO.getTitle());
    }


    @Test
    @DisplayName("Question 삭제")
    void deleteQuestion(){

        Question question = questionService.createQuestion(questionDTO);

        questionService.deleteQuestion(question.getId());

        assertThat(questionRepository.findById(question.getId())).isEqualTo(null);

    }


    private void createPageQuestion(){
        questionDTOS = new ArrayList<>();

        QuestionDTO mydto;
        String[] type = {"java", "c++"};

        for(int i = 1; i<=40; i++) {
            mydto = QuestionDTO.builder()
                    .title("problem1")
                    .type(type[i%2])
                    .build();
            questionService.createQuestion(mydto);
            if (i%2==0)questionDTOS.add(mydto);
        }
    }

}
