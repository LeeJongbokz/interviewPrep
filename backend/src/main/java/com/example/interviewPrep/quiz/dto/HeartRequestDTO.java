package com.example.interviewPrep.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HeartRequestDTO {
    @NotNull
    private Long answerId;

    @NotNull
    private Long memberId;
}
