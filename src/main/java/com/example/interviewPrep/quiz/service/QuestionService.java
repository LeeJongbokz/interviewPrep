package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.exception.QuestionNotFoundException;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;

    public Question createQuestion(QuestionDTO questionDTO){
        Question question = Question.builder()
                        .title(questionDTO.getTitle())
                        .type(questionDTO.getType())
                        .build();
        questionRepository.save(question);
        return question;
    }

    public Question updateQuestion(Long id, QuestionDTO questionDTO){

        Question question = findQuestionById(id);

        if(question == null){
            throw new QuestionNotFoundException(id);
        }

        question.change(
                    questionDTO.getTitle(),
                    questionDTO.getType()
        );

        return questionRepository.update(question);


    }

    public Question deleteQuestion(Long id){
        Question question = findQuestionById(id);
        questionRepository.delete(question);
        return question;
    }

    public Optional<List<Question>> findQuestionsByType(String type){
        List<Question> questions = questionRepository.findByType(type);

        if(questions.size() == 0){
            return Optional.empty();
        }

        return Optional.ofNullable(questions);
    }

    public Question findQuestionById(Long id){
        return questionRepository.findById(id);
    }
}
