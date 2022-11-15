package com.example.interviewPrep.quiz.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Result {
    private String responseCode;
    private String message;
    private Object data;

    public Result(String responseCode){
        this.responseCode = responseCode;
    }

    public Result(String responseCode, String message){
        this.message = message;
        this.responseCode = responseCode;
    }

    public Result(String responseCode, String message, Object data){
        this.data = data;
        this.message = message;
        this.responseCode = responseCode;
    }
}