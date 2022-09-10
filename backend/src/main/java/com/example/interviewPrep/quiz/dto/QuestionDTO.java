package com.example.interviewPrep.quiz.dto;

import com.example.interviewPrep.quiz.domain.Question;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    private Long id;

    @NotNull
    private String title;
    @NotNull
    private String type;

    public QuestionDTO(Question question) {
    }
}
