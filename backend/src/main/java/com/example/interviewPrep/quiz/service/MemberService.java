package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.exception.DuplicateEmailException;
import com.example.interviewPrep.quiz.repository.MemberRepository;
import com.example.interviewPrep.quiz.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;


    public void createMember(SignUpRequestDTO memberDTO){

            boolean duplicateResult = duplicateMember(memberDTO.getEmail());

            if(duplicateResult){
                throw new DuplicateEmailException("중복된 이메일입니다.");
            }

            String encryptedPassword = SHA256Util.encryptSHA256(memberDTO.getPassword());

            Member member = Member.builder()
                .email(memberDTO.getEmail())
                .name(memberDTO.getName())
                .type("normal")
                .password(encryptedPassword)
                .build();

            memberRepository.save(member);
    }


    public boolean duplicateMember(String email){

        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()){
            return true;
        }else{
            return false;
        }
    }
}
