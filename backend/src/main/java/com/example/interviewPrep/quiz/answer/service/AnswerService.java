package com.example.interviewPrep.quiz.answer.service;


import com.example.interviewPrep.quiz.answer.dto.SolutionDTO;
import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.dto.AnswerDTO;
import com.example.interviewPrep.quiz.aop.MemberLoginCheck;
import com.example.interviewPrep.quiz.dto.SolutionDTO;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.question.domain.Question;
import com.example.interviewPrep.quiz.question.repository.QuestionRepository;
import com.example.interviewPrep.quiz.exception.advice.CommonException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.NOT_FOUND_ANSWER;
import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.NOT_FOUND_QUESTION;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final MemberRepository memberRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @MemberLoginCheck
    public Answer createAnswer(AnswerDTO answerDTO){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        Long memberId = Long.parseLong(userDetails.getUsername());

        Member member = memberRepository.findById(memberId).get();
       
        Question question = questionRepository.findById(answerDTO.getQuestionId())
                .orElseThrow(() -> new CommonException(NOT_FOUND_QUESTION));

        Answer answer =  Answer.builder()
                .member(member)
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