package com.example.interviewPrep.quiz.answer;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
public class AnswerDTO {

    private final Long id;

    @NotNull
    private final Long questionId;

    @NotBlank
    private final String content;

}
