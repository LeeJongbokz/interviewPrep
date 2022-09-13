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
        Answer answer = answerRepository.findById(answerId);
        Heart heart = Heart.builder()
                .answer(answer)
                .build();
        heartRepository.save(heart);
        return heart;
    }
}
