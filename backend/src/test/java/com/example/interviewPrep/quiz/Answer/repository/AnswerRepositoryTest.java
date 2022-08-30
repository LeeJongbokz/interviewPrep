package com.example.interviewPrep.quiz.Answer.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    @Test
    @DisplayName("Answer를 DB에 저장")
    public void save(){

        // Given
        List<Answer> answers = new ArrayList<>();

        Answer answer1 = Answer.builder()
                .content("답안1")
                .build();

        Answer answer2 = Answer.builder()
                .content("답안2")
                .build();

        answers.add(answer1);
        answers.add(answer2);

        // When
        answerRepository.save(answers);

        // Then
        for(Answer answer: answers){
            Long savedId = answer.getId();
            assertEquals(answer, answerRepository.findById(savedId));
        }

    }



}
