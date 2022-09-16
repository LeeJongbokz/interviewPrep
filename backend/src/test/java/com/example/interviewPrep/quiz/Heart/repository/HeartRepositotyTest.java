package com.example.interviewPrep.quiz.Heart.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeartRepositotyTest {
    HeartRepository heartRepository;
    Question question;
    Answer answer;

    @Test
    void saveTest() {
        Heart heart = Heart.builder().build();
        Heart savedHeart = heartRepository.save(heart);

        assertThat(heartRepository.findById(savedHeart.getId()).orElseThrow()).isEqualTo(savedHeart);
    }


}
