package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.dto.AnswersDTO;
import com.example.interviewPrep.quiz.service.AnswerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_OK;
import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_SERVER_ERROR;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/answer/create")
    public ResponseEntity<Void> create(@RequestBody @NotNull AnswersDTO answersDTO) throws JSONException, JsonProcessingException {

        JSONArray array = new JSONArray(answersDTO.answers);
        List<AnswerDTO> answerDTOs = new ArrayList<>();

        for(int i=0; i<array.length(); i++){
            Object object = array.get(i);
            ObjectMapper objectMapper = new ObjectMapper();
            AnswerDTO answerDTO = objectMapper.readValue(object.toString(), AnswerDTO.class);
            answerDTOs.add(answerDTO);
        }

        try {
            answerService.createAnswers(answerDTOs);
            return RESPONSE_OK;
        }catch(Exception e){
            return RESPONSE_SERVER_ERROR;
        }
    }

}
