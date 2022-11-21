package com.example.interviewPrep.quiz.question;

import com.example.interviewPrep.quiz.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = @Index(name= "i_question", columnList = "title"))
public class Question extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QUESTION_ID", insertable=false, updatable=false)
    private Long id;
    private String title;
    private String type;

    public void change(String title, String type){
        this.title = title;
        this.type = type;
    }
}
