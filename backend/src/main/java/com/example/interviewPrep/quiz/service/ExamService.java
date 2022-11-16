package com.example.interviewPrep.quiz.service;

import com.example.interviewPrep.quiz.domain.Answer;
import com.example.interviewPrep.quiz.domain.Exam;
import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.infra.AnswerRepository;
import com.example.interviewPrep.quiz.infra.ExamRepository;
import com.example.interviewPrep.quiz.infra.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public List<Question> createExam() {

        questionRepository.findAll();

        return questionRepository.findRamdom();
    }

    public Exam saveExam(List<Answer> answers) {
        answerRepository.saveAll(answers);

        Exam saveExam = Exam.builder()
            .answers(answers)
            .build();

        examRepository.save(saveExam);

        return saveExam;
    }

    public Exam readExam(Long examId) {
        return examRepository.findById(examId).orElseThrow(
            () -> new IllegalArgumentException("해당 모의고사가 존재하지 않습니다.")
        );
    }
}
