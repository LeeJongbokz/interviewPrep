package com.example.interviewPrep.quiz.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {

    private static final String email = "hello@gmail.com";
    private static final String SECRET = "12345678123456781234567812345678";
    private static final String VALID_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY2NDMwMjI5NCwiZXhwIjoxNjY0MzA0MDk0LCJlbWFpbCI6ImhlbGxvQGdtYWlsLmNvbSJ9._xKOG080jGvnr-X9BS9ND4VfPIKtxWIrQ0N4pZjUjno";
    private static final String INVALID_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJmcmVzaCIsImlhdCI6MTY2NDMwMjI5NCwiZXhwIjoxNjY0MzA0MDk0LCJlbWFpbCI6ImhlbGxvQGdtYWlsLmNvbSJ9._xKOG080jGvnr-X9BS9ND4VfPIKtxWIrQ0N4pZjUjnp";

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp(){
        jwtUtil = new JwtUtil(SECRET);
    }

    @Test
    void makeJWTtoken() {

        String accessToken = jwtUtil.makeJWTtoken(email);

        assertThat(accessToken).isEqualTo(VALID_TOKEN);

    }

    @Test
    void decodeWithValidToken() {

        Claims claims = jwtUtil.decode(VALID_TOKEN);
        assertThat(claims.get("memberId", Long.class)).isEqualTo(1);
    }


    @Test
    void decodeWithInValidToken() {
        assertThatThrownBy(() -> jwtUtil.decode(INVALID_TOKEN))
                .isInstanceOf(SignatureException.class);
    }
}