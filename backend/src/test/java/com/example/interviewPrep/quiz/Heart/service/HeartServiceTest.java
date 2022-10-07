package com.example.interviewPrep.quiz.Heart.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.AnswerRepository;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.HeartRepository;
import com.example.interviewPrep.quiz.exception.AnswerNotFoundException;
import com.example.interviewPrep.quiz.exception.HeartNotFountException;
import com.example.interviewPrep.quiz.service.HeartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class HeartServiceTest {
    @Autowired HeartRepository heartRepository;
    @Autowired AnswerRepository answerRepository;
    HeartService heartService;

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = Answer.builder().build();
        answerRepository.save(answer);
        heartService = new HeartService(heartRepository, answerRepository);
    }

    @Test
    @DisplayName("좋아요 테스트")
    void create() {
        Heart savedHeart = heartService.createHeart(answer.getId());

        assertThat(heartRepository.findById(savedHeart.getId())).isPresent();
    }

    @Test
    @DisplayName("좋아요를 눌렀는데 답변이 없을경우, AnswerNotFoundException가 발생한다.")
    void create_notFoundAnswer_test() {
        assertThrows(AnswerNotFoundException.class, () -> {
            heartService.createHeart(-1L);
            });
    }

    @Test
    @DisplayName("좋아요 취소 테스트")
    void delete() {
        Heart savedHeart = heartService.createHeart(answer.getId());
        Heart deletedHeart = heartService.deleteHeart(savedHeart.getId());

        assertThat(heartRepository.findById(deletedHeart.getId())).isEmpty();
    }

    @Test
    @DisplayName("좋아요 취소를 눌렀는데 좋아요가 없다면, HeartNotFountException가 발생한다.")
    void delete_notFoundHeart_test() {
        assertThrows(HeartNotFountException.class, () -> {
            heartService.deleteHeart(-1L);
        });
    }
}
