package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.exception.AnswerNotFoundException;
import com.example.interviewPrep.quiz.exception.HeartNotFoundException;
import com.example.interviewPrep.quiz.infra.AnswerRepository;
import com.example.interviewPrep.quiz.infra.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final AnswerRepository answerRepository;

    public Heart createHeart(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() ->
            new AnswerNotFoundException("답변 정보를 찾을 수 없어 좋아요를 누를 수 없습니다."));
        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        Heart heart = Heart.builder()
            .answer(answer)
            .build();

        heartRepository.save(heart);
        return heart;
    }

    public Heart deleteHeart(Long heartId) {
        Heart heart = heartRepository.findById(heartId).orElseThrow(() ->
            new HeartNotFoundException("좋아요를 누른 기록이 없어 좋아요 취소를 할 수 없습니다."));

        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        heartRepository.delete(heart);

        return heart;
    }
}
