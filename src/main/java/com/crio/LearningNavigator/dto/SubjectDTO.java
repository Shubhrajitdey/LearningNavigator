package com.crio.LearningNavigator.dto;

import java.util.Set;

import com.crio.LearningNavigator.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;
    private String subjectName;
    private Set<Student> enrolledStudents;
}
