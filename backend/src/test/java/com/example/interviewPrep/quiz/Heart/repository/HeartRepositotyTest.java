package com.example.interviewPrep.quiz.Heart.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.infra.HeartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

public class HeartRepositotyTest {
    HeartRepository heartRepository;

    @MockBean
    Answer answer;
    Member member;

    @Test
    void saveTest() {
        Heart heart = Heart.builder()
                .answer(answer)
                .member(member)
                .build();
        Heart savedHeart = heartRepository.save(heart);

        assertThat(heartRepository.findById(savedHeart.getId()).orElseThrow()).isEqualTo(savedHeart);
    }


}
