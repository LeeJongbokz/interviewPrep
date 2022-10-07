package com.example.interviewPrep.quiz.Heart.repository;

import com.example.interviewPrep.quiz.domain.AnswerRepository;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.HeartRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class HeartRepositotyTest {
    @Autowired
    HeartRepository heartRepository;

    @Test
    void saveTest() {
        Heart heart = Heart.builder()
            .build();
        Heart savedHeart = heartRepository.save(heart);

        assertThat(heartRepository.findById(savedHeart.getId()).orElseThrow()).isEqualTo(savedHeart);
    }

    @Test
    void deleteTest() {
        Heart heart = Heart.builder()
            .build();
        Heart savedHeart = heartRepository.save(heart);

        heartRepository.delete(savedHeart);

        assertThat(heartRepository.findById(savedHeart.getId())).isEmpty();
    }


}
