package com.crio.LearningNavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crio.LearningNavigator.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long>{
    
}
