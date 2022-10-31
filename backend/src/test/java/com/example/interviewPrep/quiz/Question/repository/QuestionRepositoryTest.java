package com.example.interviewPrep.quiz.Question.repository;

import com.example.interviewPrep.quiz.domain.Question;
import com.example.interviewPrep.quiz.domain.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class QuestionRepositoryTest {

    @Autowired
    private final QuestionRepository questionRepository = mock(QuestionRepository.class);

    Question question;
    @BeforeEach
    public void setUp(){

        question = Question.builder()
                .id(1L)
                .title("problem313")
                .type("java")
                .build();
    }


    @Test
    @DisplayName("Question을 DB에 저장")
    public void save(){

        Question question2 = questionRepository.save(question);

        Long savedId = question.getId();
        assertEquals(question.getId(), question2.getId());


    }

    @Test
    @DisplayName("Question을 DB에서 삭제")
    public void delete(){

        // Given
        questionRepository.save(question);
        // When
        questionRepository.delete(question);
        Optional<Question> question2 = questionRepository.findById(question.getId());
        // Then
        assertEquals(question2, Optional.empty());

        //given(questionRepository.findById(1L)).willReturn(Optional.of(question));

    }


    @Test
    @DisplayName("Question을 id로 검색")
    public void findById() throws Exception{

        // When
        questionRepository.save(question);
        Question question2 = questionRepository.findById(question.getId()).get();

        // Then
        assertEquals(question.getId(),question2.getId() );
    }


//    @Test
//    @DisplayName("Question을 type으로 검색")
//    public void findByTitle(){
//
//        // When
//        questionRepository.save(question);
//
//        // Then
//        given(questionRepository.findByTitle("problem313")).willReturn(Optional.of(question));
//    }

    /*
    @Test
    @DisplayName("Question을 type으로 검색")
    @Transactional
    @Rollback(false)
    public void findByType(){

        // Given
        Question newQuestion = Question.builder()
                .title("problem333")
                .type("java22")
                .build();

        // When
        questionRepository.save(newQuestion);

        // Then
        List<Question> result = questionRepository.findByType("java22");
        assertThat(result.size()).isEqualTo(1);

    }
    */
}
