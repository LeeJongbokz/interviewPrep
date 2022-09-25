package com.example.interviewPrep.quiz.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SessionResponseDTO {

    private String accessToken;
}
