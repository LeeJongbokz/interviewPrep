package com.example.interviewPrep.quiz.answer.service;

import com.example.interviewPrep.quiz.answer.domain.Answer;
import com.example.interviewPrep.quiz.answer.domain.AnswerComment;
import com.example.interviewPrep.quiz.answer.dto.req.CommentReq;
import com.example.interviewPrep.quiz.answer.dto.res.CommentRes;
import com.example.interviewPrep.quiz.answer.repository.AnswerRepository;
import com.example.interviewPrep.quiz.answer.repository.CommentRepository;
import com.example.interviewPrep.quiz.exception.advice.CommonException;
import com.example.interviewPrep.quiz.member.domain.Member;
import com.example.interviewPrep.quiz.member.repository.MemberRepository;
import com.example.interviewPrep.quiz.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.interviewPrep.quiz.exception.advice.ErrorCode.*;
import static com.example.interviewPrep.quiz.utils.DateFormat.customLocalDateTime;


@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerRepository answerRepository;
    private final MemberRepository memberRepository;


    public Page<CommentRes> findAnswerComment(Long id, Pageable pageable){

        Long memberId = JwtUtil.getMemberId();
        Page<AnswerComment> comments = commentRepository.findByComment(id, pageable);
        List<CommentRes> commentList = new ArrayList<>();

        if(comments.getContent().isEmpty()) throw new CommonException(NOT_FOUND_COMMENT);

        for(AnswerComment comment: comments){
            boolean check=false;
            if(comment.getMember().getId().equals(memberId)) check=true;
            String time = customLocalDateTime(comment.getModifiedDate());

            CommentRes commentRes = CommentRes.builder()
                    .id(comment.getId())
                    .comment(comment.getComment())
                    .memberName(comment.getMember().getName())
                    .modifiedDate(time)
                    .myAnswer(check)
                    .build();
            commentList.add(commentRes);
        }

        return new PageImpl<>(commentList);
    }


    public void createComment(CommentReq commentReq){

        Long memberId = JwtUtil.getMemberId();

        Member member = findMember(memberId);
        Answer answer = findAnswer(commentReq.getAnswerId());

        AnswerComment comment = AnswerComment.builder()
                .answer(answer)
                .member(member)
                .comment(commentReq.getComment())
                .build();

        commentRepository.save(comment);
    }


    public void updateComment(CommentReq commentReq){
        AnswerComment comment = findComment(commentReq.getId());
        comment.change(commentReq.getComment());
        commentRepository.save(comment);
    }

    public void deleteComment(Long id){
        AnswerComment comment = findComment(id);
        commentRepository.delete(comment);
    }

    public AnswerComment findComment(Long id){
        return commentRepository.findById(id).orElseThrow(() -> new CommonException(NOT_FOUND_COMMENT));
    }

    public Answer findAnswer(Long id){
        return answerRepository.findById(id).orElseThrow(() -> new CommonException(NOT_FOUND_ANSWER));
    }

    public Member findMember(Long id){
        return memberRepository.findById(id).orElseThrow(()-> new CommonException(NOT_FOUND_MEMBER));
    }

}

