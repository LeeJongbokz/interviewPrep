package com.example.interviewPrep.quiz.controller;

import com.example.interviewPrep.quiz.aop.Timer;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.FilterDTO;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.repository.FilterRepository;
import com.example.interviewPrep.quiz.service.QuestionService;
import lombok.RequiredArgsConstructor;
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

import static com.example.interviewPrep.quiz.utils.ResponseEntityConstants.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://52.3.173.210")
public class QuestionController {

    private final QuestionService questionService;
    private final FilterRepository filterRepository;

    @Timer // redis 유무에 따른 api 응답시간을 체크를 위해 시간 측정 aop 사용
    @GetMapping({"/{type}", ""})
    public ResponseEntity<?> getQuestionType(@PathVariable(required = false) String type, @PageableDefault(size=10) Pageable pageable){

        if(type==null) type = "all";
        Optional<Page<QuestionDTO>> questionsDTO = questionService.findByType(type, pageable);

        if(questionsDTO.isEmpty()){
            return RESPONSE_NOT_FOUND;
        }
        return new ResponseEntity<>(questionsDTO, HttpStatus.OK);
    }


    @GetMapping("/single/{id}")
    public ResponseEntity<?> getQuestion(@PathVariable Long id){

        Optional<Question> question = questionService.getQuestion(id);
        if(question.isEmpty()) return RESPONSE_NOT_FOUND;

        QuestionDTO questionDTO = questionService.domainToDTO(question.get());
        return new ResponseEntity<>(questionDTO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid QuestionDTO questionDTO){
        try{
            questionService.createQuestion(questionDTO);
            return RESPONSE_CREATED;
        }catch(Exception e){
            return RESPONSE_SERVER_ERROR;
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid QuestionDTO questionDTO){
        try{
            questionService.updateQuestion(id, questionDTO);
            return RESPONSE_OK;
        }catch(Exception e){
            return RESPONSE_NO_CONTENT;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            questionService.deleteQuestion(id);
            return RESPONSE_OK;
        }catch (Exception e){
            return RESPONSE_NO_CONTENT;
        }
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


    @GetMapping("/filter")
    public ResponseEntity<?> getFilterLanguage(){
        List<FilterDTO> filterDTOS = new ArrayList<>();

        List<String> languages = filterRepository.findAllByLanguage();
        for(String language: languages){
            FilterDTO filterDTO = FilterDTO.builder()
                    .language(language)
                    .build();
            filterDTOS.add(filterDTO);
        }

        return new ResponseEntity<>(filterDTOS, HttpStatus.OK);
    }


}
