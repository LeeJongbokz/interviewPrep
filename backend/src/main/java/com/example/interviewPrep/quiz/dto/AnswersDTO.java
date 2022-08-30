package com.example.interviewPrep.quiz.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class AnswersDTO {
    public List<AnswerDTO> answers;
}
