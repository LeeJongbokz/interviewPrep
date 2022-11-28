package com.example.interviewPrep.quiz.member.service;

import com.example.interviewPrep.quiz.member.domain.RefreshToken;
import com.example.interviewPrep.quiz.member.dto.LoginRequestDTO;
import com.example.interviewPrep.quiz.member.dto.LoginResponseDTO;
import com.example.interviewPrep.quiz.member.dto.Role;
import com.example.interviewPrep.quiz.member.exception.LoginFailureException;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.member.repository.TokenRepository;
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

    @Autowired
    private final TokenRepository tokenRepository;

    public LoginResponseDTO login(LoginRequestDTO memberDTO) {

        String email = memberDTO.getEmail();
        String password = memberDTO.getPassword();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new LoginFailureException(email));

        String encryptedPassword = SHA256Util.encryptSHA256(password);

        boolean isSamePassword = PasswordCheck.isMatch(member.getPassword(), encryptedPassword);

        if(!isSamePassword){
            throw new LoginFailureException(email);
        }

        Long memberId = member.getId();
        Role role = member.getRole();

        String accessToken = jwtUtil.createAccessToken(memberId, role);
        String refreshToken = jwtUtil.createRefreshToken(memberId, role);

        tokenRepository.save(new RefreshToken(memberId, refreshToken));

        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
