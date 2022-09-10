package com.example.interviewPrep.quiz.dto;

import com.example.interviewPrep.quiz.domain.Question;
import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AnswersDTO {
    public List<AnswerDTO> answers;

}
