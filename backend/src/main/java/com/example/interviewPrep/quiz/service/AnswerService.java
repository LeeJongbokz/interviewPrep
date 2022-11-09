package com.example.interviewPrep.quiz.service;


import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.dto.SolutionDTO;
import com.example.interviewPrep.quiz.repository.AnswerRepository;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.repository.QuestionRepository;
import com.example.interviewPrep.quiz.dto.AnswerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
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

    public Optional<AnswerDTO> readAnswer(Long id){

        Optional<Answer> answer = answerRepository.findById(id);

        return Optional.ofNullable(AnswerDTO.builder()
                .id(answer.get().getId())
                .content(answer.get().getContent())
                .questionId(answer.get().getQuestion().getId())
                .build());
    }

    public Optional<Answer> deleteAnswer(Long id){

        Optional<Answer> answer = answerRepository.findById(id);
        answerRepository.delete(answer.get());
        return answer;

    }



    public Optional<Page<SolutionDTO>> getSolution(Long id, String type, Pageable pageable){

        Page<Answer> answers;
        //if(type.equals("all"))
            answers = answerRepository.findSolution(id,pageable);
        //else if(type.equals("my")){}


        return Optional.of(answers.map(a ->SolutionDTO.builder()
                .answerId(a.getId())
                .answer(a.getContent())
                .heartCnt(a.getHeartCnt())
                .name(a.getMember().getName())
                .build()));
    }


}