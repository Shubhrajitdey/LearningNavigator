package com.crio.LearningNavigator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.crio.LearningNavigator.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
    
}
