package com.example.interviewPrep.quiz.exam.service;

import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.dto.AnswerDTO;
import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.exam.domain.Exam;
import com.example.interviewPrep.quiz.exam.domain.ExamAnswer;
import com.example.interviewPrep.quiz.exam.domain.ExamKit;
import com.example.interviewPrep.quiz.exam.domain.ExamKitQuestion;
import com.example.interviewPrep.quiz.exam.dto.ExamKitByIdDTO;
import com.example.interviewPrep.quiz.exam.dto.ExamDTO;
import com.example.interviewPrep.quiz.exam.dto.ExamKitDTO;
import com.example.interviewPrep.quiz.exam.repository.ExamAnswerRepository;
import com.example.interviewPrep.quiz.exam.repository.ExamKitQuestionRepository;
import com.example.interviewPrep.quiz.exam.repository.ExamKitRepository;
import com.example.interviewPrep.quiz.exam.repository.ExamRepository;
import com.example.interviewPrep.quiz.exception.advice.CommonException;
import com.example.interviewPrep.quiz.exception.advice.ErrorCode;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.question.domain.Question;
import com.example.interviewPrep.quiz.question.dto.QuestionDTO;
import com.example.interviewPrep.quiz.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.interviewPrep.quiz.utils.JwtUtil.getMemberId;

@RequiredArgsConstructor
@Service
public class ExamService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;
    private final ExamKitRepository examKitRepository;
    private final ExamKitQuestionRepository examKitQuestionRepository;
    private final AnswerRepository answerRepository;
    private final ExamAnswerRepository examAnswerRepository;

    public Exam saveExam(Long kitId, List<Answer> answers) {
        answerRepository.saveAll(answers);

        Member member = memberRepository.findById(getMemberId()).orElseThrow(
            () -> new CommonException(ErrorCode.NOT_FOUND_ID));

        Exam saveExam = examRepository.save(Exam.builder().kitId(kitId).member(member).build());

        for (Answer answer : answers) {
            examAnswerRepository.save(ExamAnswer.builder()
                .exam(saveExam)
                .answer(answer)
                .build());
        }

        return saveExam;
    }

    public ExamDTO readExam(Long examId) {
        Exam exam = examRepository.findById(examId).orElseThrow(
            () -> new CommonException(ErrorCode.NOT_FOUND_EXAM)
        );

        ExamKit examKit = examKitRepository.findById(exam.getKitId()).orElseThrow();

        List<AnswerDTO> answers = examAnswerRepository.findByExamId(exam.getId()).stream().map(
            x -> AnswerDTO.builder().questionId(x.getAnswer().getId()).content(x.getAnswer().getContent()).build()
        ).collect(Collectors.toList());

        return ExamDTO.builder().title(examKit.getTitle()).answers(answers).build();
    }

    public List<ExamKitDTO> findExamKit() {
        List<ExamKit> kits = examKitRepository.findAll();

        return kits.stream().map(x -> ExamKitDTO.builder()
            .id(x.getId())
            .title(x.getTitle())
            .duration(x.getDuration())
            .questions(x.getQuestions().stream().map(q -> QuestionDTO.builder()
                .id(q.getId())
                .title(q.getQuestion().getTitle())
                .build()).collect(Collectors.toList()))
            .build()).collect(Collectors.toList());
    }

    public ExamKit saveExamKitById(ExamKitByIdDTO dto) {
        ExamKit examKit = examKitRepository.save(ExamKit.builder()
            .title(dto.getTitle())
            .duration(dto.getDuration())
            .build());

        List<Question> questions = dto.getQuestions().stream()
            .map(x -> questionRepository.findById(x).orElseThrow(
                () -> new CommonException(ErrorCode.NOT_FOUND_QUESTION)))
            .collect(Collectors.toList());

        for (Question question : questions) {
            examKitQuestionRepository.save(ExamKitQuestion.builder()
                .examKit(examKit)
                .question(question)
                .build());
        }

        return examKit;
    }

    public List<QuestionDTO> findQuestions(Long id) {
        List<Question> questions = examKitQuestionRepository.findByExamKitId(id).stream()
            .map(ExamKitQuestion::getQuestion).collect(Collectors.toList());

        return questions.stream().map(x -> QuestionDTO.builder().id(x.getId()).title(x.getTitle()).type(x.getType()).build())
            .collect(Collectors.toList());
    }

    public List<ExamDTO> findMyExam() {
        Long memberId = getMemberId();
        return examRepository.findByMemberId(memberId).stream().map(x -> ExamDTO.builder()
            .title(examKitRepository.findById(x.getKitId()).get().getTitle())
            .id(x.getId())
            .build())
            .collect(Collectors.toList());
    }
}
