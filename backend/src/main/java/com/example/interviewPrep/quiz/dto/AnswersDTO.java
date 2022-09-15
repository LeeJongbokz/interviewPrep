package com.example.interviewPrep.quiz.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswersDTO {
    public final List<AnswerDTO> answers;

}
