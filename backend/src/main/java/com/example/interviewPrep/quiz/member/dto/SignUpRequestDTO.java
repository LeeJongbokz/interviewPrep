package com.example.interviewPrep.quiz.member.dto;

import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.utils.SHA256Util;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDTO {

    private String name;
    private String email;
    private String password;
    private String passwordConfirm;

    private Role role;

    public static boolean hasNullDataBeforeSignup(SignUpRequestDTO member) {
        return member.getName() == null || member.getEmail() == null
                || member.getPassword() == null || member.getPasswordConfirm() == null;
    }

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
