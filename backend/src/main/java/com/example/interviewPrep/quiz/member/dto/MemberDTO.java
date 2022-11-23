package com.example.interviewPrep.quiz.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberDTO {

    private final Long id;

    @NotNull
    private final String email;
    @NotNull
    private final String password;
    @NotNull
    private final String type;

}
