package com.example.interviewPrep.quiz.service;


import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.repository.AnswerJpaRepository;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerJpaRepository answerJpaRepository;

    public List<Answer> createAnswers(List<AnswerDTO> answerDTOs){

        List<Answer> answers = new ArrayList<>();

        for(AnswerDTO answerDTO: answerDTOs){

            Question question = new Question();
            question.setId(answerDTO.getQuestionId());

            Answer answer =  Answer.builder()
                    .question(question)
                    .content(answerDTO.getContent())
                    .build();

            answers.add(answer);
        }

        answerRepository.save(answers);
        return answers;
    }

    public Answer createAnswer(AnswerDTO answerDTO){


        Question question = new Question();
        question.setId(((Number) answerDTO.getQuestionId()).longValue());

        Answer answer =  Answer.builder()
                .question(question)
                .content(answerDTO.getContent())
                .build();

        answerJpaRepository.save(answer);
        return answer;
    }


}
