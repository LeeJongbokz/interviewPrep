package com.example.interviewPrep.quiz.service;


import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Member;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import com.example.interviewPrep.quiz.dto.QuestionDTO;
import com.example.interviewPrep.quiz.repository.AnswerJpaRepository;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.repository.QuestionJpaRepository;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerJpaRepository answerJpaRepository;
    private final QuestionJpaRepository questionJpaRepository;

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


        Optional<Question> question = questionJpaRepository.findById(answerDTO.getQuestionId());

        Answer answer =  Answer.builder()
                .question(question.get())
                .content(answerDTO.getContent())
                .build();

        answerJpaRepository.save(answer);
        return answer;
    }


}
