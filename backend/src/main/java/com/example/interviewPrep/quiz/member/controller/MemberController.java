package com.example.interviewPrep.quiz.member.controller;


import com.example.interviewPrep.quiz.member.dto.*;
import com.example.interviewPrep.quiz.member.service.AuthenticationService;
import com.example.interviewPrep.quiz.member.service.MemberService;
import com.example.interviewPrep.quiz.member.social.service.OauthService;
import com.example.interviewPrep.quiz.response.ResultResponse;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    public ResultResponse<?> signUp(@RequestBody @Valid SignUpRequestDTO memberDTO){
        return ResultResponse.success(memberService.createMember(memberDTO));
    }

    @GetMapping("userInfo")
    public ResultResponse<?> getUserInfo(){
        return ResultResponse.success(memberService.getUserInfo());
    }

    @PostMapping("login")
    public ResultResponse<?> login(@RequestBody @NotNull LoginRequestDTO memberDTO){
        return ResultResponse.success(authService.login(memberDTO));
    }

    @PutMapping("/change")
    public ResultResponse<?> changeNickNameAndEmail(@RequestBody @NotNull MemberDTO memberDTO){
        return ResultResponse.success(memberService.changeNickNameAndEmail(memberDTO));
    }

    @PutMapping("/password/change")
    public ResultResponse<?> changePassword(@RequestBody @NotNull MemberDTO memberDTO){
        return ResultResponse.success(memberService.changePassword(memberDTO));
    }

}
