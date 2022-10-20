package com.example.interviewPrep.quiz.Member.controller;

import com.example.interviewPrep.quiz.dto.SignUpRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class MemberControllerTest {


    SignUpRequestDTO validMember;
    SignUpRequestDTO invalidMember;

    @BeforeEach
    public void setUp(){

        validMember = new SignUpRequestDTO("이종복", "s2feeling@naver.com", "1234", "1234");
        invalidMember = new SignUpRequestDTO(null, "s2feeling@naver.com", "1234", "1234");
    }


    @Test
    public void signUpWithValidAttributes(){

        assertThat(SignUpRequestDTO.hasNullDataBeforeSignup(validMember)).isEqualTo(false);

    }

    @Test
    public void signUpWithInvalidAttributes(){

        assertThat(SignUpRequestDTO.hasNullDataBeforeSignup(invalidMember)).isEqualTo(true);

    }

}
