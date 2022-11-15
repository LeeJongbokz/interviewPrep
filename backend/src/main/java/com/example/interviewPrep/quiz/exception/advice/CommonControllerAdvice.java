package com.example.interviewPrep.quiz.exception.advice;

import com.example.interviewPrep.quiz.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice("com.example.interviewPrep.quiz")
public class CommonControllerAdvice {

    @ExceptionHandler()
    public ResponseEntity<Result> commonExHandler(RuntimeException ex) {
        log.error("[exceptionHandler] ex:", ex);
        return ResponseEntity.ok(new Result("800", ex.getMessage()));
    }

}
