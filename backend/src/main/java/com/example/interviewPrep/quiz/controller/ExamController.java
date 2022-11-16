package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.service.ExamService;
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
    public ResponseEntity<?> createExam() {
        return new ResponseEntity<>(examService.createExam(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> saveExam(@RequestBody List<Answer> answerList) {
        return new ResponseEntity<>(examService.saveExam(answerList), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readExam(@PathVariable Long id) {
        return new ResponseEntity<>(examService.readExam(id), HttpStatus.OK);
    }
}
