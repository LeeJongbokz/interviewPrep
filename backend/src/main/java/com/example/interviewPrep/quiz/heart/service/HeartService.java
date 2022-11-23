package com.example.interviewPrep.quiz.heart.service;

import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.exception.AnswerNotFoundException;
import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.heart.dto.HeartRequestDTO;
import com.example.interviewPrep.quiz.heart.domain.Heart;
import com.example.interviewPrep.quiz.heart.exception.HeartNotFoundException;
import com.example.interviewPrep.quiz.heart.repository.HeartRepository;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final AnswerRepository answerRepository;

    private final MemberRepository memberRepository;

    public Heart createHeart(HeartRequestDTO heartDTO) {
        Answer answer = answerRepository.findById(heartDTO.getAnswerId()).orElseThrow(() ->
            new AnswerNotFoundException("답변 정보를 찾을 수 없어 좋아요를 누를 수 없습니다."));
        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        Heart heart = Heart.builder()
            .answer(answer)
            .build();

        heartRepository.save(heart);
        return heart;
    }

    public Heart deleteHeart(HeartRequestDTO heartDTO) {
        Heart heart = heartRepository.findByAnswerIdAndMemberId(heartDTO.getAnswerId(), heartDTO.getMemberId()).orElseThrow(() ->
            new HeartNotFoundException("좋아요를 누른 기록이 없어 좋아요 취소를 할 수 없습니다."));

        //TODO 멤버 정보 가져오기 - 좋아요 기록 검증
        heartRepository.delete(heart);

        return heart;
    }
}
