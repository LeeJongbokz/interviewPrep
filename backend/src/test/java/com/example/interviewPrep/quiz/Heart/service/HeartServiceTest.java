package com.example.interviewPrep.quiz.Heart.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.exception.AnswerNotFoundException;
import com.example.interviewPrep.quiz.exception.HeartNotFountException;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import com.example.interviewPrep.quiz.service.HeartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HeartServiceTest {

    private HeartRepository heartRepository = mock(HeartRepository.class);
    @Autowired
    AnswerRepository answerRepository;
    HeartService heartService;

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = Answer.builder().build();
        answerRepository.save(answer);
        heartService = new HeartService(heartRepository, answerRepository);

        Heart heart = Heart.builder()
                .id(1L)
                .build();

        when(heartRepository.findById(1L)).thenReturn(Optional.of(heart));
    }

    // 테스트 완료
    @Test
    @DisplayName("좋아요 테스트")
    void create() {
        heartService.createHeart(answer.getId());

        verify(heartRepository).save(any(Heart.class));
    }

    // 테스트 완료
    @Test
    @DisplayName("좋아요를 눌렀는데 답변이 없을경우, AnswerNotFoundException가 발생한다.")
    void create_notFoundAnswer_test() {
        assertThrows(AnswerNotFoundException.class, () -> {
            heartService.createHeart(-1L);
        });
    }


    // 테스트 완료
    @Test
    @DisplayName("좋아요 취소 테스트")
    void delete() {
        heartService.deleteHeart(1L);

        verify(heartRepository).delete(any(Heart.class));
    }

    // 테스트 완료
    @Test
    @DisplayName("좋아요 취소를 눌렀는데 좋아요가 없다면, HeartNotFountException가 발생한다.")
    void delete_notFoundHeart_test() {
        assertThrows(HeartNotFountException.class, () -> {
            heartService.deleteHeart(-1L);
        });
    }

    @Test
    @DisplayName("하나의 답변에 동시에 좋아요가 눌렸을때, ")
    void concurrency_test() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                try {
                    heartService.createHeart(answer.getId());
                } finally {
                    latch.countDown();
                }
            });

        }
        latch.await();

        // assertThat(heartRepository.countHeartByAnswerId(answer.getId())).isEqualTo(100);
    }
}
