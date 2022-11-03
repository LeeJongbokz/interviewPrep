package com.example.interviewPrep.quiz.Heart.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.dto.HeartDTO;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import com.example.interviewPrep.quiz.repository.MemberRepository;
import com.example.interviewPrep.quiz.service.HeartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class HeartServiceConcurrencyTest {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    HeartRepository heartRepository;
    @Autowired
    MemberRepository memberRepository;

    HeartService heartService;

    Answer answer;
    Member member;

    @BeforeEach
    void setUp() {
        answer = Answer.builder()
            .build();
        member = Member.builder()
            .email("test@gmail.com")
            .build();
        answerRepository.save(answer);
        memberRepository.save(member);

        heartService = new HeartService(heartRepository, answerRepository, memberRepository);
    }

    @Test
    void increaseOptimisticLockTest() throws InterruptedException {
        int threadCount = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executorService.submit(() -> {
                try {
                    heartService.createHeart(HeartDTO.builder()
                        .answerId(answer.getId()).memberId(member.getId()).build());
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