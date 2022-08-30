package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.RESPONSE_NOT_FOUND;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{type}")
    public ResponseEntity<Void> getTest(@PathVariable String type){

        ResponseEntity responseEntity;

        Optional<List<Question>> questions = questionService.findQuestionsByType(type);

        if(!questions.isPresent()){
            return RESPONSE_NOT_FOUND;
        }

        List<QuestionDTO> questionDTOs = getQuestionDTOs(questions);
        responseEntity = new ResponseEntity<>(questionDTOs, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody @Valid QuestionDTO questionDTO){
        return questionService.createQuestion(questionDTO);
    }

    @PutMapping
    public Question update(@RequestBody @Valid QuestionDTO questionDTO){
        Long id = questionDTO.getId();
        return questionService.updateQuestion(id, questionDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    public List<QuestionDTO> getQuestionDTOs(Optional<List<Question>> questions){
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        int len = questions.get().size();

        for(int i=0; i<len; i++){
            Question question = questions.get().get(i);
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .build();
            questionDTOs.add(questionDTO);
        }

        return questionDTOs;
    }

}
