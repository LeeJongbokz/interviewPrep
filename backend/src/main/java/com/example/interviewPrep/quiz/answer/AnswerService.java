package com.example.interviewPrep.quiz.answer;


import com.example.interviewPrep.quiz.exception.advice.CommonException;
import com.example.interviewPrep.quiz.question.Question;
import com.example.interviewPrep.quiz.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.NOT_FOUND_ANSWER;
import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.NOT_FOUND_QUESTION;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public Answer createAnswer(AnswerDTO answerDTO){

        Question question = questionRepository.findById(answerDTO.getQuestionId())
                .orElseThrow(() -> new CommonException(NOT_FOUND_QUESTION));
        Answer answer =  Answer.builder()
                .question(question)
                .content(answerDTO.getContent())
                .build();

        answerRepository.save(answer);
        return answer;
    }

    public AnswerDTO readAnswer(Long id){

        Answer answer = findByAnswer(id);

        return AnswerDTO.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .questionId(answer.getQuestion().getId())
                .build();
    }


    public Answer deleteAnswer(Long id){

        Answer answer = findByAnswer(id);
        answerRepository.delete(answer);
        return answer;

    }


    public Page<SolutionDTO> getSolution(Long id, String type, Pageable pageable){

        Page<Answer> answers;
        //if(type.equals("all"))
        answers = answerRepository.findSolution(id,pageable);
        //else if(type.equals("my")){}

        if(answers.getContent().isEmpty()) throw new CommonException(NOT_FOUND_ANSWER);

        return answers.map(a ->SolutionDTO.builder()
                .answerId(a.getId())
                .answer(a.getContent())
                .heartCnt(a.getHeartCnt())
                .name(a.getMember().getName())
                .build());
    }

    public Answer findByAnswer(Long id){
        return answerRepository.findById(id).orElseThrow(() -> new CommonException(NOT_FOUND_ANSWER));
    }


}