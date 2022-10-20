package com.example.interviewPrep.quiz.controller;


import com.example.interviewPrep.quiz.dto.LoginRequestDTO;
import com.example.interviewPrep.quiz.dto.LoginResponseDTO;
import com.example.interviewPrep.quiz.dto.SignUpRequestDTO;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import com.example.interviewPrep.quiz.service.MemberService;
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

    @PostMapping("signup")
    public ResponseEntity<Void> signUp(@RequestBody @NotNull SignUpRequestDTO member){

        if(SignUpRequestDTO.hasNullDataBeforeSignup(member)){
            throw new NullPointerException("회원가입시 필수 데이터를 모두 입력해야 합니다.");
        }
        memberService.createMember(member);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @NotNull LoginRequestDTO member){

        ResponseEntity<LoginResponseDTO> responseEntity = null;
        String token = "";

        if(member == null){
            responseEntity = new ResponseEntity<>(toResponse(token), HttpStatus.UNAUTHORIZED);
        }else{

            try {
                String email = member.getEmail();
                String password = member.getPassword();

                token = authService.login(email, password);
                responseEntity = new ResponseEntity<>(toResponse(token), HttpStatus.OK);
            }catch(RuntimeException re){
                log.error("login Error:" + responseEntity);
            }
        }


        return responseEntity;
    }

    private LoginResponseDTO toResponse(String token){
        return LoginResponseDTO.builder()
                .accessToken(token)
                .build();
    }

}
