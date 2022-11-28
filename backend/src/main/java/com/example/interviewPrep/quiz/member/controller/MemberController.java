package com.example.interviewPrep.quiz.member.controller;


import com.example.interviewPrep.quiz.member.dto.*;
import com.example.interviewPrep.quiz.member.service.AuthenticationService;
import com.example.interviewPrep.quiz.member.service.MemberService;
import com.example.interviewPrep.quiz.response.ResultResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/members/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MemberController {
    private final AuthenticationService authService;
    private final MemberService memberService;

    @PostMapping("signup")
    public ResultResponse<?> signUp(@RequestBody @NotNull SignUpRequestDTO memberDTO){

        if(memberDTO.hasNullDataBeforeSignup(memberDTO)){
            throw new NullPointerException("회원 가입시 필수 데이터를 모두 입력해야 합니다.");
        }
        return ResultResponse.success(memberService.createMember(memberDTO));
    }


    @PostMapping("login")
    public ResultResponse<?> login(@RequestBody @NotNull LoginRequestDTO memberDTO){
        return ResultResponse.success(authService.login(memberDTO));
    }

    @PutMapping("/nickname/change")
    public ResultResponse<?> changeNickName(@RequestBody @NotNull MemberDTO memberDTO){
        return ResultResponse.success(memberService.changeNickName(memberDTO));
    }

    @PutMapping("/email/change")
    public ResultResponse<?> changeEmail(@RequestBody @NotNull MemberDTO memberDTO){
        return ResultResponse.success(memberService.changeEmail(memberDTO));
    }

    @PutMapping("/password/change")
    public ResultResponse<?> changePassword(@RequestBody @NotNull MemberDTO memberDTO){
        return ResultResponse.success(memberService.changePassword(memberDTO));
    }

}
