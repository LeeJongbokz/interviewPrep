package com.example.interviewPrep.quiz.utils;

import com.example.interviewPrep.quiz.errors.InvalidTokenException;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JwtUtilTest {

    private static final String SECRET = "12345678901234567890123456789010";
    private static final String email = "hello@gmail.com";
    private static String accessToken;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp(){
        jwtUtil = new JwtUtil(SECRET);
        accessToken = jwtUtil.makeJWTtoken(email);
    }

    @Test
    void makeJWTtoken() {
        assertThat(jwtUtil.isTokenValid(accessToken)).isTrue();
    }

    @Test
    void decodeWithValidToken() {
        Claims claims = jwtUtil.decode(accessToken);
        assertThat(claims.get("email")).isEqualTo(email);
    }


    @Test
    void decodeWithInValidToken() {
        assertThatThrownBy(() -> jwtUtil.decode(accessToken+"d"))
                .isInstanceOf(InvalidTokenException.class);
    }

    @Test
    void decodeWithEmptyToken(){
        assertThatThrownBy(() -> jwtUtil.decode(null))
                .isInstanceOf(InvalidTokenException.class);

        assertThatThrownBy(() -> jwtUtil.decode(" "))
                .isInstanceOf(InvalidTokenException.class);

        assertThatThrownBy(() -> jwtUtil.decode("   "))
                .isInstanceOf(InvalidTokenException.class);

    }
}