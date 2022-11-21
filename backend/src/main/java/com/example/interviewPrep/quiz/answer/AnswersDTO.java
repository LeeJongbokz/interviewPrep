package com.example.interviewPrep.quiz.answer;

import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class AnswersDTO {
    public final List<AnswerDTO> answers;

}
