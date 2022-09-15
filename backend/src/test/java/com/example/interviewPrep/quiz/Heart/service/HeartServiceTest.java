package com.example.interviewPrep.quiz.Heart.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

public class HeartServiceTest {
    @MockBean
    Question question;
    Answer answer;

    @BeforeEach
    void setUp() {
        answer = Answer.builder()
                .id(1L)
                .question(question)
                .build();
    }

    @Test
    void create() {
        Heart heart = Heart.builder()
                .answer(answer)
                .build();

        assertThat(heart.getAnswer().getId()).isEqualTo(1L);
    }
}
