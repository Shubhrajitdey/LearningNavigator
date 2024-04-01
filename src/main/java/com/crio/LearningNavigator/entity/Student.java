package com.crio.LearningNavigator.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String registrationId;
    private String name;

    @ManyToMany
    Set<Subject> enrolledSubjects = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "Student_Exam_Mapping", joinColumns = @JoinColumn(name = "student_id"), 
        	inverseJoinColumns = @JoinColumn(name = "exam_id"))
    Set<Exam> registeredExams = new HashSet<>();

    public boolean addSubject(Subject subject) {
        return enrolledSubjects.add(subject);
    }

    public boolean enrollStudentToExam(Exam exam) {
        return registeredExams.add(exam);
    }
}
