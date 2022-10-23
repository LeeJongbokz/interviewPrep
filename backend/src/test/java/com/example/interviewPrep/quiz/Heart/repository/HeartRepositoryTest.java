package com.example.interviewPrep.quiz.Heart.repository;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HeartRepositoryTest {
    @Autowired
    HeartRepository heartRepository;

    @MockBean
    Answer answer;
    @MockBean
    Member member;

    @Test
    void saveTest() {
        Heart heart = Heart.builder()
            .id(1L)
//            .answer(answer)
//            .member(member)
            .build();
        Heart savedHeart = heartRepository.save(heart);

        assertThat(heartRepository.findById(savedHeart.getId()).isPresent());
    }


}
