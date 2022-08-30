package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.dto.AnswersDTO;
import com.example.interviewPrep.quiz.service.AnswerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import java.util.List;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_OK;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_SERVER_ERROR;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @NotNull AnswersDTO answersDTO) throws JSONException, JsonProcessingException {

        List<AnswerDTO> answerDTOs = answersDTO.getAnswers();

        try {
            answerService.createAnswers(answerDTOs);
            return RESPONSE_OK;
        }catch(Exception e){
            return RESPONSE_SERVER_ERROR;
        }
    }

}
