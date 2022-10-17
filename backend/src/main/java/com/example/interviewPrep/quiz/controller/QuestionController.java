package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.aop.Timer;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    @Autowired
    private final QuestionService questionService;

    @Timer
    @GetMapping("/{type}")
    public ResponseEntity<?> getQuestionType(@PathVariable String type, @PageableDefault(size=10) Pageable pageable){

        Optional<Page<QuestionDTO>> questionsDTO = questionService.findByType(type, pageable);

        if(questionsDTO.isEmpty()){
            return RESPONSE_NOT_FOUND;
        }

        return new ResponseEntity<>(questionsDTO, HttpStatus.OK);
    }


    @GetMapping("/single/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id){

        Question question = questionService.getQuestion(id);
        QuestionDTO questionDTO = questionService.domainToDTO(question);

        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody @Valid QuestionDTO questionDTO){
        return questionService.createQuestion(questionDTO);
    }

    @PutMapping("{id}")
    public Question update(@PathVariable Long id, @RequestBody @Valid QuestionDTO questionDTO){
        return questionService.updateQuestion(id, questionDTO);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    private List<QuestionDTO> getQuestionDTOs(List<Question> questions){
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        for(Question question: questions){
            QuestionDTO questionDTO = QuestionDTO.builder()
                    .id(question.getId())
                    .title(question.getTitle())
                    .build();
            questionDTOs.add(questionDTO);
        }

        return questionDTOs;
    }


}
