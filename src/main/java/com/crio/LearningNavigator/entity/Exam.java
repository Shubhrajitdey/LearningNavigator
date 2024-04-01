package com.crio.LearningNavigator.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    /** Unique identifier for the exam. */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String id;

    @ManyToOne
    private Subject subject;
    
    @JsonIgnore 
    @ManyToMany
    @JoinTable(name = "Student_Exam_Mapping", joinColumns = @JoinColumn(name = "exam_id"), 
        	inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> registeredStudents = new HashSet<>();

    public boolean registerStudent(Student student) {
        return this.registeredStudents.add(student);
    }
}
