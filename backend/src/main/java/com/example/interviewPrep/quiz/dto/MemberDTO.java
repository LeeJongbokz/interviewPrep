package com.example.interviewPrep.quiz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
public class MemberDTO {

    private Long id;

    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String type;

}
