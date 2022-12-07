package com.example.interviewPrep.quiz.exam.dto;

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
public class ExamKitREQ {
    private Long id;
    private String title;
    private int duration;
    private List<Long> questions = new ArrayList<>();
}
