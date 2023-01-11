package com.example.interviewPrep.quiz.member.service;

import com.example.interviewPrep.quiz.exception.advice.CommonException;
import com.example.interviewPrep.quiz.member.dto.MemberDTO;
import com.example.interviewPrep.quiz.member.exception.LoginFailureException;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public Member createMember(SignUpRequestDTO memberDTO){

            boolean duplicatedEmail = isDuplicatedEmail(memberDTO.getEmail());
            if(duplicatedEmail){
                throw new CommonException(DUPLICATE_EMAIL);
            }

            Member member = memberDTO.toEntity();
            memberRepository.save(member);
            return member;
    }

    public boolean isDuplicatedEmail(String email){

            Optional<Member> member = memberRepository.findByEmail(email);

            if(member.isPresent()){
                return true;
            }

            return false;
    }


    public Member getUserInfo(){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        Long memberId = Long.parseLong(userDetails.getUsername());
        Member member = memberRepository.findById(memberId).get();

        Member userInfo = Member.builder()
                          .email(member.getEmail())
                          .name(member.getName())
                          .nickName(member.getNickName())
                          .build();
        return userInfo;
    }
    public Member changeNickNameAndEmail(MemberDTO memberDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        Long memberId = Long.parseLong(userDetails.getUsername());

        Member member = memberRepository.findById(memberId).get();


        String newNickName = memberDTO.getNickName();
        String newEmail = memberDTO.getEmail();

        if(newNickName != null){
            boolean duplicateNickName = memberRepository.existsByNickName(newNickName);
            if(duplicateNickName){
                throw new CommonException(DUPLICATE_NICKNAME);
            }
            member.setNickName(newNickName);
        }

        if(newEmail != null){
            boolean duplicateEmail = memberRepository.existsByEmail(newEmail);
            if(duplicateEmail){
                throw new CommonException(DUPLICATE_EMAIL);
            }
            member.setEmail(newEmail);
        }

        memberRepository.save(member);

        return member;
    }


    public Member changeEmail(MemberDTO memberDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;

        Long memberId = Long.parseLong(userDetails.getUsername());

        Member member = memberRepository.findById(memberId).get();

        String newEmail = memberDTO.getEmail();
        member.setEmail(newEmail);
        memberRepository.save(member);

        return member;
    }


    public Member changePassword(MemberDTO memberDTO){

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
            return member;
        }else{
            throw new LoginFailureException(member.getEmail());
        }

    }

}
