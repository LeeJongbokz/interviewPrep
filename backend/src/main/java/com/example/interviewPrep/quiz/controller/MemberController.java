package com.example.interviewPrep.quiz.controller;


import com.example.interviewPrep.quiz.dto.LoginRequestDTO;
import com.example.interviewPrep.quiz.dto.LoginResponseDTO;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController("/login")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://52.3.173.210")
@Slf4j
public class MemberController {
    private final AuthenticationService authService;
    @PostMapping
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
                responseEntity = new ResponseEntity<>(toResponse(token), HttpStatus.UNAUTHORIZED);
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
