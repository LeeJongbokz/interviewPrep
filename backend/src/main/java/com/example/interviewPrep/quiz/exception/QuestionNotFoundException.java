package com.example.interviewPrep.quiz.exception;

public class QuestionNotFoundException extends RuntimeException{

    public QuestionNotFoundException(Long id) {
        super("Question not found:" + id);
    }
}
