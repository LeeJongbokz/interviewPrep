package com.example.interviewPrep.quiz.question;

public class QuestionNotFoundException extends RuntimeException{

    public QuestionNotFoundException(Long id) {
        super("Question not found:" + id);
    }
}
