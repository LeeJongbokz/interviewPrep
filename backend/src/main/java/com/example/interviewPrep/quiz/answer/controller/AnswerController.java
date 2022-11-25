package com.example.interviewPrep.quiz.answer.controller;

import com.example.interviewPrep.quiz.answer.dto.AnswerDTO;
import com.example.interviewPrep.quiz.answer.service.AnswerService;
import com.example.interviewPrep.quiz.aop.MemberLoginCheck;
import com.example.interviewPrep.quiz.utils.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping()
    @MemberLoginCheck
    public ResponseEntity<Result> createAnswer(@RequestBody @Valid AnswerDTO answerDTO){
        return ResponseEntity.ok(
                new Result("200", "답안 작성", answerService.createAnswer(answerDTO)));

    }


    @GetMapping("/{id}")
    public ResponseEntity<Result> readAnswer(@RequestBody @Valid AnswerDTO answerDTO){

        return ResponseEntity.ok(new Result("200", "답안 조회", answerService.readAnswer(answerDTO)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Result> deleteAnswer(@RequestBody @Valid AnswerDTO answerDTO){

        return ResponseEntity.ok(new Result("200", "답안 삭제", answerService.deleteAnswer(answerDTO)));
    }


    @GetMapping("/solution/{id}/{type}")
    public ResponseEntity<?> findSolutionAnswer(@PathVariable Long id, @PathVariable String type,
                                                @PageableDefault(size=10) Pageable pageable){

        return ResponseEntity.ok(new Result("200", "다른 사용자 답안 조회", answerService.getSolution(id, type, pageable)));
    }

}