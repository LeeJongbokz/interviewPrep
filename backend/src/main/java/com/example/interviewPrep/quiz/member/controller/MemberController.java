package com.example.interviewPrep.quiz.member.controller;


import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.domain.RefreshToken;
import com.example.interviewPrep.quiz.member.dto.LoginRequestDTO;
import com.example.interviewPrep.quiz.member.dto.LoginResponseDTO;
import com.example.interviewPrep.quiz.member.dto.Role;
import com.example.interviewPrep.quiz.member.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.member.repository.TokenRepository;
import com.example.interviewPrep.quiz.member.service.AuthenticationService;
import com.example.interviewPrep.quiz.member.service.MemberService;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/members/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MemberController {
    private final AuthenticationService authService;
    private final MemberService memberService;

    private final TokenRepository jpaTokenRepository;

    private final JwtUtil jwtUtil;

    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@RequestBody @NotNull SignUpRequestDTO member){

        if(member.hasNullDataBeforeSignup(member)){
            throw new NullPointerException("회원가입시 필수 데이터를 모두 입력해야 합니다.");
        }
        memberService.createMember(member);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @NotNull LoginRequestDTO member){

        ResponseEntity<LoginResponseDTO> responseEntity = null;
        String accessToken = "";
        String refreshToken = "";

        if(member == null){
            responseEntity = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }else{

            try {
                String email = member.getEmail();
                String password = member.getPassword();

                Member searchedMember = authService.login(email, password);
                Long memberId = searchedMember.getId();
                Role role = searchedMember.getRole();

                accessToken = jwtUtil.createAccessToken(memberId, role);
                refreshToken = jwtUtil.createRefreshToken(memberId, role);

                jpaTokenRepository.save(new RefreshToken(refreshToken));

                responseEntity = ResponseEntity.ok()
                                .body(toResponse(accessToken, refreshToken));

            }catch(RuntimeException re){
                log.error("login Error:" + responseEntity);
            }
        }


        return responseEntity;
    }

    private LoginResponseDTO toResponse(String accessToken, String refreshToken){
        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
