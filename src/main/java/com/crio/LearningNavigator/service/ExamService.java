package com.crio.LearningNavigator.service;

import java.util.List;

import com.crio.LearningNavigator.dto.EmptyBodyDTO;
import com.crio.LearningNavigator.dto.ExamDTO;
import com.crio.LearningNavigator.dto.RegisterStudentInExamDTO;

public interface ExamService {
    List<ExamDTO> getExams();
    ExamDTO addExam(Long subjectId);
    ExamDTO registerStudent(Long examId, RegisterStudentInExamDTO student);
    ExamDTO getExamById(Long examId);
    EmptyBodyDTO deleteExam(Long examId);
}