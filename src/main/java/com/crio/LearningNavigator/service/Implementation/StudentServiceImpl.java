package com.crio.LearningNavigator.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.crio.LearningNavigator.dto.EmptyBodyDTO;
import com.crio.LearningNavigator.dto.StudentDTO;
import com.crio.LearningNavigator.dto.StudentRequestDTO;
import com.crio.LearningNavigator.entity.Student;
import com.crio.LearningNavigator.entity.Subject;
import com.crio.LearningNavigator.exception.ResourceNotFoundException;
import com.crio.LearningNavigator.repository.StudentRepository;
import com.crio.LearningNavigator.repository.SubjectRepository;
import com.crio.LearningNavigator.service.StudentService;

public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentDTO addStudent(StudentRequestDTO entity) {
        Student newStudent = new Student();
        newStudent.setName(entity.getName());
        Student savedStudent = studentRepository.save(newStudent);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(id)));
        return modelMapper.map(student, StudentDTO.class);        
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = new ArrayList<>();
        students.forEach(exam -> {
            studentDTOs.add(
                modelMapper.map(exam, StudentDTO.class)
            );
        });
        return studentDTOs;
    }

    @Override
    public StudentDTO updateStudent(Long studentId, StudentDTO studentDTO) {
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }

        if(studentDTO == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(studentId)));

        student.setName(studentDTO.getName());
        Student savedStudent = studentRepository.save(student);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public StudentDTO enrollStudentToSubject(Long studentId, Long subjectId) {
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        if(subjectId == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(studentId)));
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(subjectId)));
        
        student.addSubject(subject);
        subject.addStudent(student);
        Student savedStudent = studentRepository.save(student);
        subjectRepository.save(subject);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Override
    public EmptyBodyDTO delete(Long studentId) {
        if(studentId == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        Student student = studentRepository.findById(studentId)
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(studentId)));
        studentRepository.delete(student);
        return new EmptyBodyDTO();
    }
}
