package com.crio.LearningNavigator.dto;

import java.util.Set;

import com.crio.LearningNavigator.entity.Student;
import com.crio.LearningNavigator.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ExamDTO {
    private Long id;
    private Subject subject;
    private Set<Student> registeredStudents;
}
