package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {
    @Autowired
    private final HeartRepository heartRepository;

    @Autowired
    private final AnswerRepository answerRepository;

    public Heart createHeart(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow();
        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        Heart heart = Heart.builder()
                .answer(answer)
                .question(answer.getQuestion())
                .build();

        heartRepository.save(heart);
        return heart;
    }

    public Heart deleteHeart(Long heartId) {
        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        Heart heart = heartRepository.findById(heartId).orElseThrow();
        heartRepository.delete(heart);
        return heart;
    }
}
