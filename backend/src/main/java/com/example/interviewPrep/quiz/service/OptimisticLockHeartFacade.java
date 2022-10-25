package com.example.interviewPrep.quiz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OptimisticLockHeartFacade {
    private final HeartService heartService;

    public void increaseHeartWithOptimisticLock(Long answerId) throws InterruptedException {
        while (true) {
            try {
                heartService.increaseHeartWithOptimisticLock(answerId);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
