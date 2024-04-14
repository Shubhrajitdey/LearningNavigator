package com.crio.LearningNavigator.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @JsonBackReference
    @ManyToMany(mappedBy = "registeredExams")
    @JoinTable(name = "Student_Exam_Mapping", joinColumns = @JoinColumn(name = "exam_id"), 
        	inverseJoinColumns = @JoinColumn(name = "student_id"))
    Set<Student> registeredStudents = new HashSet<>();

    public boolean registerStudent(Student student) {
        return this.registeredStudents.add(student);
    }

}
