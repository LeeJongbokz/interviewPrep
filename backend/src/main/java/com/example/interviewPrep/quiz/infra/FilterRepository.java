package com.example.interviewPrep.quiz.infra;

import com.example.interviewPrep.quiz.domain.Filter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Filter, Long>{

    @Query("SELECT f.language FROM Filter f ORDER BY f.language ASC")
    List<String> findAllByLanguage();
}
