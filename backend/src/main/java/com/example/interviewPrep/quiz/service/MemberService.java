package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.MemberRepository;
import com.example.interviewPrep.quiz.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
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

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException(memberId));

        member.updateToken(refreshToken);

        memberRepository.save(member);
    }
}
