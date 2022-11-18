package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.aop.Timer;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.service.QuestionService;
import com.example.interviewPrep.quiz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    @Timer // redis 유무에 따른 api 응답시간을 체크를 위해 시간 측정 aop 사용
    @GetMapping({"/{type}", ""})
    public ResponseEntity<Result> getQuestionType(@PathVariable(required = false) String type,
                                                  @PageableDefault(size=10) Pageable pageable){

        return ResponseEntity.ok(
                new Result("200", "타입별 문제 조회", questionService.findByType(type, pageable)));
    }


    @GetMapping("/single/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id){

        return ResponseEntity.ok(new Result("200", "단일 문제 조회", questionService.getQuestion(id)));
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid QuestionDTO questionDTO){

        return ResponseEntity.ok(new Result("200", "문제 작성", questionService.createQuestion(questionDTO)));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid QuestionDTO questionDTO){

        return ResponseEntity.ok(new Result("200", "문제 수정", questionService.updateQuestion(id, questionDTO)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        return ResponseEntity.ok(new Result("200", "문제 삭제", questionService.deleteQuestion(id)));
    }


    @GetMapping("/filter")
    public ResponseEntity<?> getFilterLanguage(){

        return ResponseEntity.ok(new Result("200", "문제 언어 리스트 조회", questionService.findFilterLanguage()));
    }


}