package com.example.interviewPrep.quiz.controller;


import com.example.interviewPrep.quiz.dto.MemberDTO;
import com.example.interviewPrep.quiz.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.Optional;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_OK;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_NOT_FOUND;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody @NotNull MemberDTO memberInfo){

        String email = memberInfo.getEmail();
        String password = memberInfo.getPassword();

        Optional<MemberDTO> searchedMemberDTO = memberService.loginByEmailAndPassword(email, password);
        if(searchedMemberDTO.isPresent()){
            return RESPONSE_OK;
        }else {
            return RESPONSE_NOT_FOUND;
        }

    }

}
