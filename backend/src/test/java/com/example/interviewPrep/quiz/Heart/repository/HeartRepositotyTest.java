package com.example.interviewPrep.quiz.Heart.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HeartRepositotyTest {

    @Autowired
    HeartRepository heartRepository;

    @MockBean
    Answer answer;
    Heart heart;

    @BeforeEach
    void setUp() {
        heartRepository = new HeartRepository();
        heart = Heart.builder()
                .id(1L)
                .answer(answer)
                .build();
    }

    @Test
    void save() {
        heartRepository.save(heart);

        assertThat(heartRepository.findById(1L).orElseThrow().getId()).isEqualTo(1L);
    }
}
