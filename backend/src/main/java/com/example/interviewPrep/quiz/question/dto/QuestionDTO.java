package com.example.interviewPrep.quiz.question.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
public class QuestionDTO {

    private final Long id;

    @NotNull
    private final String title;
    @NotNull
    private final String type;

}
