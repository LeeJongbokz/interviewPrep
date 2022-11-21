package com.example.interviewPrep.quiz.member.service;

import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.member.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public void createMember(SignUpRequestDTO memberDTO){

            Member member = Member.builder()
                .email(memberDTO.getEmail())
                .password(memberDTO.getPassword())
                .build();

            memberRepository.save(member);
    }


    public void updateRefreshToken(Long memberId, String refreshToken){

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException("멤버 정보를 찾을 수 없습니다."));

        member.updateToken(refreshToken);

        memberRepository.save(member);
    }
}
