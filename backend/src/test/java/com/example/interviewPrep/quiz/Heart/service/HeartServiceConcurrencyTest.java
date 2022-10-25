package com.example.interviewPrep.quiz.Heart.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import com.example.interviewPrep.quiz.service.HeartService;
import com.example.interviewPrep.quiz.service.OptimisticLockHeartFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Rollback(value = false)
public class HeartServiceConcurrencyTest {
    private Logger log = LoggerFactory.getLogger(HeartServiceConcurrencyTest.class);
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    HeartRepository heartRepository;

    HeartService heartService;

    @Autowired
    OptimisticLockHeartFacade optimisticLockHeartFacade;

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = Answer.builder()
            .build();
        answerRepository.save(answer);

        heartService = new HeartService(heartRepository, answerRepository);
    }

    @Test
    void increaseOptimisticLockTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    optimisticLockHeartFacade.increaseHeartWithOptimisticLock(answer.getId());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();

        assertEquals(100, answerRepository.findById(answer.getId()).orElseThrow().getCountHeart());
    }
}
