package com.example.interviewPrep.quiz.member.service;

import com.example.interviewPrep.quiz.member.dto.MemberDTO;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.member.exception.MemberNotFoundException;
import com.example.interviewPrep.quiz.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public void createMember(SignUpRequestDTO memberDTO){

            String encryptedPassword = SHA256Util.encryptSHA256(memberDTO.getPassword());

            Member member = memberDTO.toEntity();

            memberRepository.save(member);
    }


    public void changeNickName(MemberDTO memberDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        Long memberId = Long.parseLong(userDetails.getUsername());

        Member member = memberRepository.findById(memberId).get();

        String newNickName = memberDTO.getNickName();
        member.setNickName(newNickName);
        memberRepository.save(member);
    }


    public void changeEmail(MemberDTO memberDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        Long memberId = Long.parseLong(userDetails.getUsername());

        Member member = memberRepository.findById(memberId).get();

        String newEmail = memberDTO.getEmail();
        member.setEmail(newEmail);
        memberRepository.save(member);

    }

    public void changePassword(MemberDTO memberDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        Long memberId = Long.parseLong(userDetails.getUsername());

        Member member = memberRepository.findById(memberId).get();

        String password = memberDTO.getPassword();
        String newPassword = memberDTO.getNewPassword();
        String encryptedPassword = SHA256Util.encryptSHA256(password);

        if(member.getPassword().equals(encryptedPassword)){
            String newEncryptedPassword = SHA256Util.encryptSHA256(newPassword);
            member.setPassword(newEncryptedPassword);
            memberRepository.save(member);
        }

    }



    public void updateRefreshToken(Long memberId, String refreshToken){

        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotFoundException("멤버 정보를 찾을 수 없습니다."));

        member.updateToken(refreshToken);

        memberRepository.save(member);
    }
}
