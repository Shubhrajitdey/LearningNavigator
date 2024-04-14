package com.crio.LearningNavigator.service.Implementation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.crio.LearningNavigator.dto.EmptyBodyDTO;
import com.crio.LearningNavigator.dto.ExamDTO;
import com.crio.LearningNavigator.dto.RegisterStudentInExamDTO;
import com.crio.LearningNavigator.entity.Exam;
import com.crio.LearningNavigator.entity.Student;
import com.crio.LearningNavigator.entity.Subject;
import com.crio.LearningNavigator.exception.ResourceNotFoundException;
import com.crio.LearningNavigator.repository.ExamRepository;
import com.crio.LearningNavigator.repository.StudentRepository;
import com.crio.LearningNavigator.repository.SubjectRepository;
import com.crio.LearningNavigator.service.ExamService;

public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<ExamDTO> getExams() {
        List<Exam> exams = examRepository.findAll();
        List<ExamDTO> ExamDTOs = new ArrayList<>();
        exams.forEach(exam -> {
            ExamDTOs.add(
                map(exam)
            );
        });
        return ExamDTOs;
    }

    @Override
    public ExamDTO getExamById(Long examId) {
        if(examId == null) {
            throw new IllegalArgumentException("Exam ID cannot be null");
        }
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        // return modelMapper.map(exam, ExamDto.class);
        return map(exam);
    }

    @Override
    public ExamDTO addExam(Long subjectId) {
        if(subjectId == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(subjectId)));
        Exam exam = new Exam();
        exam.setSubject(subject);
        Exam savedExam = examRepository.save(exam);
        // return modelMapper.map(savedExam, ExamDTO.class);
        return map(savedExam);

    }

    @Override
    public ExamDTO registerStudent(Long examId, RegisterStudentInExamDTO entity) {
        if(examId == null) {
            throw new IllegalArgumentException("Exam ID cannot be null");
        }
        if(entity == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        if(entity.getStudentId() == null) {
            throw new IllegalArgumentException("Student ID cannot be null");
        }
        
        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        Student student = studentRepository.findById(entity.getStudentId())
            .orElseThrow(() -> new ResourceNotFoundException("Student", "Student Id", Long.toString(entity.getStudentId())));
        
        // Check if the student is enrolled in the subject of the exam
        if (!student.getEnrolledSubjects().contains(exam.getSubject())) {
            throw new IllegalStateException("Student is not enrolled in the subject of this exam.");
        }

        exam.registerStudent(student);
        examRepository.save(exam);
        return map(exam);
    }

    @Override
    public EmptyBodyDTO deleteExam(Long examId) {
        if(examId == null) {
            throw new IllegalArgumentException("Exam ID cannot be null");
        }

        Exam exam = examRepository.findById(examId)
            .orElseThrow(() -> new ResourceNotFoundException("Exam", "Exam Id", Long.toString(examId)));
        
        examRepository.delete(exam);
        return new EmptyBodyDTO();
    }


    private ExamDTO map(Exam exam) {
        ExamDTO ExamDTO = new ExamDTO();
        ExamDTO.setId(exam.getId());
        ExamDTO.setSubject(exam.getSubject());
        ExamDTO.setRegisteredStudents(exam.getRegisteredStudents());
        return ExamDTO;
    }
}
