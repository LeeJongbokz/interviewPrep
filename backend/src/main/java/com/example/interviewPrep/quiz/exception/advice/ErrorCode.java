package com.example.interviewPrep.quiz.exception.advice;

import lombok.Getter;

@Getter
public enum ErrorCode {


    NOT_FOUND_ANSWER("not_found_answer", "answer를 찾을 수 없습니다."),
    NOT_FOUND_QUESTION("not_found_question", "question을 찾을 수 없습니다."),

    MISSING_PARAMETER("missing_parameter", "");

    private final String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCode setMissingParameterMsg(String msg) {
        if (this.equals(MISSING_PARAMETER)) {
            setMessage(msg);
        }
        return this;
    }

    private void setMessage(String msg) {
        this.message = msg;
    }
}
