package com.example.interviewPrep.quiz.exam.controller;

import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.exam.service.ExamService;
import com.example.interviewPrep.quiz.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exam")
@CrossOrigin("*")
public class ExamController {
    private final ExamService examService;

    @GetMapping()
    public ResultResponse<?> createExam() {
        return ResultResponse.success(examService.createExam());
    }

    @PostMapping()
    public ResultResponse<?> saveExam(@RequestBody List<Answer> answerList) {
        return ResultResponse.success(examService.saveExam(answerList));
    }

    @GetMapping("/{id}")
    public ResultResponse<?> readExam(@PathVariable Long id) {
        return ResultResponse.success(examService.readExam(id));
    }
}
