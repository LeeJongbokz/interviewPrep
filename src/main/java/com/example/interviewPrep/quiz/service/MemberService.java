package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.dto.MemberDTO;
import com.example.interviewPrep.quiz.repository.MemberRepository;
import com.example.interviewPrep.quiz.utils.PasswordCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

        List<Member> searchedMembers = memberRepository.findByEmail(member.getEmail());

        if(searchedMembers.isEmpty()){
            return Optional.empty();
        }
        boolean isSamePassword = PasswordCheck.isMatch(searchedMembers.get(0).getPassword(), password);

        if(!isSamePassword){
            return Optional.empty();
        }

        Optional<MemberDTO> searchedMemberDTO = Optional.ofNullable(MemberDTO.builder()
                .email(searchedMembers.get(0).getEmail())
                .password(searchedMembers.get(0).getPassword())
                .build());

        return searchedMemberDTO;

    }
}
