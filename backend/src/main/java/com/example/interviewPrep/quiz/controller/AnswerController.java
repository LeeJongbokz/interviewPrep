package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AnswerController {

    private final AnswerService answerService;
    @PostMapping("/{id}")
    public ResponseEntity<?> create(@RequestBody @Valid AnswerDTO answerDTO){

        try {
            answerService.createAnswer(answerDTO);
            return RESPONSE_CREATED;
        }catch (Exception e){
            return RESPONSE_SERVER_ERROR;
        }
    }


    /*
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
    */

}
