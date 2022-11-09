package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.dto.SolutionDTO;
import com.example.interviewPrep.quiz.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping()
    public ResponseEntity<?> createAnswer(@RequestBody @Valid AnswerDTO answerDTO){

        try {
            answerService.createAnswer(answerDTO);
            return RESPONSE_CREATED;
        }catch (Exception e){
            return RESPONSE_SERVER_ERROR;
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> readAnswer(@PathVariable Long id){

        Optional<AnswerDTO> answerDTO = answerService.readAnswer(id);
        if(answerDTO.isEmpty()) return RESPONSE_NO_CONTENT;
        return new ResponseEntity<>(answerDTO, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long id){

        Optional<Answer> answer = answerService.deleteAnswer(id);
        if(answer.isEmpty()) return RESPONSE_NO_CONTENT;
        return RESPONSE_OK;
    }


    @GetMapping("/solution/{id}/{type}")
    public ResponseEntity<?> findSolutionAnswer(@PathVariable Long id, @PathVariable String type,
                                                @PageableDefault(size=10) Pageable pageable){

        Optional<Page<SolutionDTO>> answerDTOs = answerService.getSolution(id, type, pageable);
        if(answerDTOs.isEmpty()) return RESPONSE_NO_CONTENT;
        return new ResponseEntity<>(answerDTOs, HttpStatus.OK);
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
