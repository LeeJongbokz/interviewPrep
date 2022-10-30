package com.example.interviewPrep.quiz.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private Long id;

    @NotNull
    private Long questionId;

    @NotBlank
    private String content;

}
