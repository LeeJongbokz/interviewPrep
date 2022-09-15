package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.domain.Heart;
import com.example.interviewPrep.quiz.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/{id}/heart")
    public Heart create(@PathVariable Long id){
        Long answerId = id;

        return heartService.createHeart(answerId);
    }
}
