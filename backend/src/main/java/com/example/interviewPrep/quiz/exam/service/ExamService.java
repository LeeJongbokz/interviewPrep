package com.example.interviewPrep.quiz.exam.service;

import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.exam.domain.Exam;
import com.example.interviewPrep.quiz.exam.domain.ExamAnswer;
import com.example.interviewPrep.quiz.exam.repository.ExamAnswerRepository;
import com.example.interviewPrep.quiz.exam.repository.ExamRepository;
import com.example.interviewPrep.quiz.question.domain.Question;
import com.example.interviewPrep.quiz.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ExamAnswerRepository examAnswerRepository;

    public List<Question> createExam() {

        questionRepository.findAll();

        return questionRepository.findRandom();
    }

    public Exam saveExam(List<Answer> answers) {
        answerRepository.saveAll(answers);

        Exam saveExam = Exam.builder()
            .build();

        examRepository.save(saveExam);

        for (Answer answer : answers) {
            examAnswerRepository.save(ExamAnswer.builder()
                .exam(saveExam)
                .answer(answer)
                .build());
        }

        return saveExam;
    }

    public Exam readExam(Long examId) {
        return examRepository.findById(examId).orElseThrow(
            () -> new IllegalArgumentException("해당 모의고사가 존재하지 않습니다.")
        );
    }
}
