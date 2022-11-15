package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_CREATED;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_OK;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_SERVER_ERROR;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@CrossOrigin(origins =  "*")
@Log4j2
public class HeartController {
    private final HeartService heartService;

    @PostMapping("/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        Long answerId = id;
        try {
            heartService.createHeart(answerId);
            return RESPONSE_CREATED;
        } catch (Exception e) {
            return RESPONSE_SERVER_ERROR;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Long answerId = id;
        try {
            heartService.deleteHeart(answerId);
            return RESPONSE_OK;
        } catch (Exception e) {
            return RESPONSE_SERVER_ERROR;
        }
    }


}
