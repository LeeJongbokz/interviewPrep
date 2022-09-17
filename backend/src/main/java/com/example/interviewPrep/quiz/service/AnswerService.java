package com.example.interviewPrep.quiz.service;


import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    public Answer createAnswer(AnswerDTO answerDTO){


        Optional<Question> question = questionRepository.findById(answerDTO.getQuestionId());

        Answer answer =  Answer.builder()
                .question(question.get())
                .content(answerDTO.getContent())
                .build();

        answerRepository.save(answer);
        return answer;
    }


}