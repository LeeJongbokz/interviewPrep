package com.example.interviewPrep.quiz.controller;


import com.example.interviewPrep.quiz.dto.LoginRequestDTO;
import com.example.interviewPrep.quiz.dto.LoginResponseDTO;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
    private final AuthenticationService authService;
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @NotNull LoginRequestDTO member){

        String email = member.getEmail();
        String password = member.getPassword();

        String token = authService.login(email, password);

        return toResponse(token);
    }

    private LoginResponseDTO toResponse(String token){
        return LoginResponseDTO.builder()
                .accessToken(token)
                .build();
    }

}
