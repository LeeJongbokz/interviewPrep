package com.example.interviewPrep.quiz.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private final Long id;

    @NotNull
    private final Long questionId;

    @NotBlank
    private final String content;


}
