package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_CREATED;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_SERVER_ERROR;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id){
        Long answerId = id;
        try {
            heartService.createHeart(answerId);
            return RESPONSE_CREATED;
        }catch (Exception e){
            return RESPONSE_SERVER_ERROR;
        }
    }
}
