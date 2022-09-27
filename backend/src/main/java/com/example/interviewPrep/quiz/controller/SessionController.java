package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.dto.SessionResponseDTO;
import com.example.interviewPrep.quiz.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    private AuthenticationService authenticationService;

    public SessionController(AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SessionResponseDTO login(){

        String accessToken = authenticationService.login();

        return SessionResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
