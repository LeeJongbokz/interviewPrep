package com.example.interviewPrep.quiz.heart.service;

import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.exception.advice.CommonException;
import com.example.interviewPrep.quiz.heart.dto.HeartRequestDTO;
import com.example.interviewPrep.quiz.heart.domain.Heart;
import com.example.interviewPrep.quiz.heart.repository.HeartRepository;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.*;
import static com.example.interviewPrep.quiz.utils.JwtUtil.*;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final AnswerRepository answerRepository;

    private final MemberRepository memberRepository;

    public boolean createHeart(HeartRequestDTO heartDTO) throws InterruptedException {
        Long memberId = getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new CommonException(NOT_FOUND_MEMBER));
        if (heartRepository.findByAnswerIdAndMemberId(heartDTO.getAnswerId(), memberId).isPresent()) {
            throw new CommonException(EXIST_HEART_HISTORY);
        }

        Answer answer = increaseHeartWithOptimisticLock(heartDTO.getAnswerId());

        heartRepository.save(Heart.builder().answer(answer).member(member).build());

        return true;
    }

    public boolean deleteHeart(HeartRequestDTO heartDTO) {
        Long memberId = getMemberId();
        Member member = memberRepository.findById(memberId).orElseThrow(() ->
            new CommonException(NOT_FOUND_MEMBER));
        Heart heart = heartRepository.findByAnswerIdAndMemberId(heartDTO.getAnswerId(), memberId).orElseThrow(() ->
            new CommonException(NOT_EXIST_HEART_HISTORY));

        Answer answer = decreaseHeartWithOptimisticLock(heartDTO.getAnswerId());

        heartRepository.delete(heart);

        return true;
    }

    @Transactional
    public Answer increaseHeartWithOptimisticLock(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() ->
            new CommonException(NOT_FOUND_ANSWER));

        answer.increase();

        return answerRepository.saveAndFlush(answer);
    }

    @Transactional
    public Answer decreaseHeartWithOptimisticLock(Long answerId) {
        Answer answer = answerRepository.findById(answerId).orElseThrow(() ->
            new CommonException(NOT_FOUND_ANSWER));

        answer.decrease();

        return answerRepository.saveAndFlush(answer);
    }
}
