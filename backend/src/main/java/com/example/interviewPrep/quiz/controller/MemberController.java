package com.example.interviewPrep.quiz.controller;


import com.example.interviewPrep.quiz.dto.LoginRequestDTO;
import com.example.interviewPrep.quiz.dto.LoginResponseDTO;
import com.example.interviewPrep.quiz.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import com.example.interviewPrep.quiz.service.MemberService;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

                Long memberId = authService.login(email, password);
                accessToken = jwtUtil.createAccessToken(memberId);
                refreshToken = jwtUtil.createRefreshToken(memberId);

                memberService.updateRefreshToken(memberId, refreshToken);

                ResponseCookie cookie = ResponseCookie.from("refreshToken",refreshToken)
                        .httpOnly(true)
                        .secure(true)
                        .path("/")
                        .maxAge(60)
                        .build();

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.SET_COOKIE, cookie.toString());

                responseEntity = ResponseEntity.ok()
                                .headers(headers)
                                .body(toResponse(accessToken));

            }catch(RuntimeException re){
                log.error("login Error:" + responseEntity);
            }
        }


        return responseEntity;
    }

    private LoginResponseDTO toResponse(String acceesToken){
        return LoginResponseDTO.builder()
                .accessToken(acceesToken)
                .build();
    }

}
