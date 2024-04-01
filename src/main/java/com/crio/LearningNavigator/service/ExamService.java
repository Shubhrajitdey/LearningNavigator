package com.crio.LearningNavigator.service;

import java.util.List;

import com.crio.LearningNavigator.dto.ExamDTO;
import com.crio.LearningNavigator.dto.RegisterStudentInExamDTO;

public interface ExamService {
    List<ExamDTO> getExams();
    ExamDTO addExam(String subjectId);
    ExamDTO registerStudent(String examId, RegisterStudentInExamDTO student);

    /**Implementation plan for future release
    ExamDTO getExamById(String examId);
    EmptyBodyDTO deleteExam(String examId);**/
}