package com.crio.LearningNavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crio.LearningNavigator.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{
    
}
