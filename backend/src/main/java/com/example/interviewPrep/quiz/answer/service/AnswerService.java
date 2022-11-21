package com.example.interviewPrep.quiz.answer.service;


import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.dto.AnswerDTO;
import com.example.interviewPrep.quiz.dto.SolutionDTO;
import com.example.interviewPrep.quiz.question.domain.Question;
import com.example.interviewPrep.quiz.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    public Answer createAnswer(AnswerDTO answerDTO){

        Question question = questionRepository.findById(answerDTO.getQuestionId()).get();

        Answer answer =  Answer.builder()
                .question(question)
                .content(answerDTO.getContent())
                .build();

        answerRepository.save(answer);
        return answer;
    }

    public AnswerDTO readAnswer(AnswerDTO answerDTO){

        Long id = answerDTO.getId();

        Answer answer = answerRepository.findById(id).get();

        return AnswerDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(answer.getQuestion().getId())
                .build();
    }


    public Answer deleteAnswer(AnswerDTO answerDTO){

        Long id = answerDTO.getId();

        Answer answer = answerRepository.findById(id).get();

        answerRepository.delete(answer);
        return answer;

    }


    public Page<SolutionDTO> getSolution(Long id, String type, Pageable pageable){

        Page<Answer> answers;
        //if(type.equals("all"))
        answers = answerRepository.findSolution(id,pageable);
        //else if(type.equals("my")){}


        return answers.map(a ->SolutionDTO.builder()
                .answerId(a.getId())
                .answer(a.getContent())
                .heartCnt(a.getHeartCnt())
                .name(a.getMember().getName())
                .build());
    }


}