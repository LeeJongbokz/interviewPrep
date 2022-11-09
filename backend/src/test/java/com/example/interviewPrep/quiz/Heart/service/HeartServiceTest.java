package com.example.interviewPrep.quiz.Heart.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.dto.HeartDTO;
import com.example.interviewPrep.quiz.exception.AnswerNotFoundException;
import com.example.interviewPrep.quiz.exception.HeartNotFoundException;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import com.example.interviewPrep.quiz.repository.MemberRepository;
import com.example.interviewPrep.quiz.service.HeartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HeartServiceTest {

    @Mock
    HeartRepository heartRepository;
    @Mock
    AnswerRepository answerRepository;
    @Mock
    MemberRepository memberRepository;

    HeartService heartService;


    @BeforeEach
    void setUp() {
        heartService = new HeartService(heartRepository, answerRepository, memberRepository);

        Answer answer = Answer.builder()
            .id(1L)
            .build();
        Member member = Member.builder()
            .id(1L)
            .email("test@gmail.com")
            .build();

        when(answerRepository.findById(1L)).thenReturn(Optional.of(answer));
        when(answerRepository.findByIdWithOptimisticLock(1L)).thenReturn(Optional.of(answer));
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));

    }

    @Test
    @DisplayName("좋아요 테스트")
    void create() throws InterruptedException {
        Heart savedHeart = heartService.createHeart(HeartDTO.builder().answerId(1L).build());

        verify(heartRepository).save(savedHeart);
    }

    @Test
    @DisplayName("좋아요를 눌렀는데 답변이 없을경우, AnswerNotFoundException가 발생한다.")
    void create_notFoundAnswer_test() {
        assertThrows(AnswerNotFoundException.class, () -> {
            heartService.createHeart(HeartDTO.builder().answerId(-1L).build());
        });
    }

    @Test
    @DisplayName("좋아요 취소 테스트")
    void delete() throws InterruptedException {
        Heart heart = Heart.builder()
            .id(1L)
            .build();
        when(heartRepository.findById(1L)).thenReturn(Optional.of(heart));

        heartService.deleteHeart(HeartDTO.builder().answerId(1L).build());

        verify(heartRepository).delete(any(Heart.class));
    }

    @Test
    @DisplayName("좋아요 취소를 눌렀는데 좋아요가 없다면, HeartNotFountException가 발생한다.")
    void delete_notFoundHeart_test() {
        assertThrows(HeartNotFoundException.class, () -> {
            heartService.deleteHeart(HeartDTO.builder().answerId(-1L).build());
        });
    }
}
