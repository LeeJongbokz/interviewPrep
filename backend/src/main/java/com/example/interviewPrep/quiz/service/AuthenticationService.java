package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.repository.MemberRepository;
import com.example.interviewPrep.quiz.exception.LoginFailureException;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import com.example.interviewPrep.quiz.utils.PasswordCheck;
import com.example.interviewPrep.quiz.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final JwtUtil jwtUtil;
    @Autowired
    private final MemberRepository memberRepository;
    public Long login(String email, String password) {
        Member searchedMember = memberRepository.findByEmail(email)
                .orElseThrow(()-> new LoginFailureException(email));

        String encryptedPassword = SHA256Util.encryptSHA256(password);

        boolean isSamePassword = PasswordCheck.isMatch(searchedMember.getPassword(), encryptedPassword);

        if(!isSamePassword){
            throw new LoginFailureException(email);
        }

        return searchedMember.getId();
    }
}
