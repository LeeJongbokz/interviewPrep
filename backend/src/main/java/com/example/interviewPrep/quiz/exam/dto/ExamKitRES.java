package com.example.interviewPrep.quiz.exam.dto;

import com.example.interviewPrep.quiz.question.dto.QuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExamKitRES {
    private Long id;
    private String title;
    private int duration;
    private List<QuestionDTO> questions = new ArrayList<>();
}
