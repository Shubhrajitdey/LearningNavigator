package com.crio.LearningNavigator.dto;

import java.util.Set;

import com.crio.LearningNavigator.entity.Exam;
import com.crio.LearningNavigator.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private Set<Subject> subjects;
    private Set<Exam> exams;
}
