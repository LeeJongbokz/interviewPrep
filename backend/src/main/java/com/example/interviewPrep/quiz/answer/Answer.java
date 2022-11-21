package com.example.interviewPrep.quiz.answer;

import com.example.interviewPrep.quiz.domain.BaseTimeEntity;
import com.example.interviewPrep.quiz.member.Member;
import com.example.interviewPrep.quiz.question.Question;
import com.example.interviewPrep.quiz.heart.HeartExistException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ANSWER_ID")
    private Long id;

    @Lob
    private String content;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "QUESTION_ID")
    Question question;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "MEMBER_ID")
    Member member;

    private int heartCnt;

    public void change(String content){
        this.content = content;
    }

    public int increase() {
        return ++this.heartCnt;
    }

    public int decrease() {
        if (this.heartCnt <= 0) {
            throw new HeartExistException("좋아요 수가 0보다 작아 좋아요 수를 감소시킬수 없습니다.");
        }
        return --this.heartCnt;
    }

}
