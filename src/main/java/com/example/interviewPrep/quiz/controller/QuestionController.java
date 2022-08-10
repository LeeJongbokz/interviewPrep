package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class QuestionController {

    @Autowired
    private final QuestionService questionService;


    @GetMapping("/{type}")
    public String getTest(Model model, @PathVariable String type){
        List<Question> questions = questionService.findQuestionsByType(type);
        model.addAttribute("questions", questions);
        return "test";
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Question create(@RequestBody @Valid QuestionDTO questionDTO){
        return questionService.createQuestion(questionDTO);
    }

    @PutMapping("/update")
    public Question update(@RequestBody @Valid QuestionDTO questionDTO){
        Long id = questionDTO.getId();
        return questionService.updateQuestion(id, questionDTO);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }


}