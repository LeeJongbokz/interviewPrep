package com.example.interviewPrep.quiz.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class SolutionDTO {

    private final Long answerId;
    private final String answer;
    private final String name;
    private final int heartCnt;

}
