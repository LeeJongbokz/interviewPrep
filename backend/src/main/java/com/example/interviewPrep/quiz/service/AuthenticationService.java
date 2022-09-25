package com.example.interviewPrep.quiz.service;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class AuthenticationService {

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String login() {
        return "a.b.c";
    }
}
