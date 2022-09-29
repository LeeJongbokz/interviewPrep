package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.MemberRepository;
import com.example.interviewPrep.quiz.dto.MemberDTO;
import com.example.interviewPrep.quiz.utils.PasswordCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Optional<MemberDTO> loginByEmailAndPassword(String email, String password){
        Member member = Member.builder()
                .email(email)
                .password(password)
                .build();

        Optional<Member> searchedMember = memberRepository.findByEmailAndType(member.getEmail(),"normal");

        if(searchedMember.isEmpty()){
            return Optional.empty();
        }
        
        boolean isSamePassword = PasswordCheck.isMatch(searchedMember.get().getPassword(), password);

        if(!isSamePassword){
            return Optional.empty();
        }

        Optional<MemberDTO> searchedMemberDTO = Optional.ofNullable(MemberDTO.builder()
                .email(searchedMember.get().getEmail())
                .password(searchedMember.get().getPassword())
                .build());

        return searchedMemberDTO;

    }
}
