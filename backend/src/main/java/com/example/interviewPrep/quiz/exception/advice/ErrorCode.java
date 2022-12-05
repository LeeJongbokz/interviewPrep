package com.example.interviewPrep.quiz.exception.advice;

import lombok.Getter;

@Getter
public enum ErrorCode {


    NOT_FOUND_ANSWER("not_found_answer", "answer를 찾을 수 없습니다."),
    NOT_FOUND_QUESTION("not_found_question", "question을 찾을 수 없습니다."),
    NOT_FOUND_MEMBER("not_found_member", "member를 찾을 수 없습니다."),
    NOT_FOUND_COMMENT("not_found_comment", "comment를 찾을 수 없습니다."),
    NOT_FOUND_EXAM("not_found_exam", "해당 모의고사를 찾을 수 없습니다."),
    NOT_FOUND_ID("not_found_id", "로그인된 ID를 찾을 수 없습니다."),
    NOT_FOUND_TYPE("not_found_type", "해당 type으로 조회할 수 없습니다."),

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
