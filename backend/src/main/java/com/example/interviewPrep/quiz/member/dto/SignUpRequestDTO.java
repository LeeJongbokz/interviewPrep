package com.example.interviewPrep.quiz.member.dto;

import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.utils.SHA256Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String passwordConfirm;

    private Role role;

    public Member toEntity() {
        Member member = Member.builder()
                .email(email)
                .name(name)
                .password(SHA256Util.encryptSHA256(password))
                .nickName(name)
                .role(role.USER)
                .build();
        return member;
    }

}
