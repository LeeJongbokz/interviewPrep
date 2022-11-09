package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.dto.HeartRequestDTO;
import com.example.interviewPrep.quiz.exception.AnswerNotFoundException;
import com.example.interviewPrep.quiz.exception.HeartNotFoundException;
import com.example.interviewPrep.quiz.exception.MemberNotFoundException;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.HeartRepository;
import com.example.interviewPrep.quiz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;

    public Heart createHeart(HeartRequestDTO heartDTO) throws InterruptedException {
        Answer answer = answerRepository.findById(heartDTO.getAnswerId()).orElseThrow(() ->
            new AnswerNotFoundException("답변 정보를 찾을 수 없어 좋아요를 누를 수 없습니다."));
        Member member = memberRepository.findById(heartDTO.getMemberId()).orElseThrow(() ->
            new MemberNotFoundException("멤버 정보를 찾을 수 없어 좋아요를 누를 수 없습니다."));

        Heart heart = Heart.builder()
            .answer(answer)
            .member(member)
            .build();

        increaseHeartFacade(answer.getId());

        heartRepository.save(heart);
        return heart;
    }

    public Heart deleteHeart(HeartRequestDTO heartDTO) throws InterruptedException {
        Heart heart = heartRepository.findByAnswerIdAndMemberId(heartDTO.getAnswerId(), heartDTO.getMemberId()).orElseThrow(() ->
            new HeartNotFoundException("좋아요를 누른 기록이 없어 좋아요 취소를 할 수 없습니다."));
        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        decreaseHeartFacade(heartDTO.getAnswerId());
        heartRepository.delete(heart);

        return heart;
    }

    @Transactional
    public void increaseHeartWithOptimisticLock(Long answerId) {
        Answer answer = answerRepository.findByIdWithOptimisticLock(answerId).orElseThrow(() ->
            new AnswerNotFoundException("답변 정보를 찾을 수 없어 좋아요를 누를 수 없습니다."));

        answer.increase();

        answerRepository.saveAndFlush(answer);
    }

    @Transactional
    public void decreaseHeartWithOptimisticLock(Long answerId) {
        Answer answer = answerRepository.findByIdWithOptimisticLock(answerId).orElseThrow(() ->
            new AnswerNotFoundException("답변 정보를 찾을 수 없어 좋아요를 누를 수 없습니다."));

        answer.decrease();

        answerRepository.saveAndFlush(answer);
    }

    public void increaseHeartFacade(Long answerId) throws InterruptedException {
        while (true) {
            try {
                increaseHeartWithOptimisticLock(answerId);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }

    public void decreaseHeartFacade(Long answerId) throws InterruptedException {
        while (true) {
            try {
                decreaseHeartWithOptimisticLock(answerId);
                break;
            } catch (Exception e) {
                Thread.sleep(50);
            }
        }
    }
}
