package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.service.HeartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_CREATED;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_OK;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_SERVER_ERROR;

@RestController
@RequestMapping("/heart")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class HeartController {
    private final HeartService heartService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @NotNull AnswerDTO answerDTO) {

        Long answerId = answerDTO.getId();
        try {
            heartService.createHeart(answerId);
            return RESPONSE_CREATED;
        } catch (Exception e) {
            log.error("error is" + e.getMessage());
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
