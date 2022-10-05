package com.example.interviewPrep.quiz.utils;

import com.example.interviewPrep.quiz.errors.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final Key key;
    public JwtUtil(@Value("${jwt.secret}") String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }
    public String makeJWTtoken(String email){

        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("fresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+ Duration.ofMinutes(30).toMillis()))
                .claim("email", email)
                .signWith(key)
                .compact();
    }

    public Claims decode(String token){

        if(token == null || token.isBlank()){
            throw new InvalidTokenException(token);
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(SignatureException e){
            throw new InvalidTokenException(token);
        }
    }

    public boolean isTokenValid(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            log.error("유효하지 않은 Token입니다", e.getMessage());
            return false;
        }
    }

}
