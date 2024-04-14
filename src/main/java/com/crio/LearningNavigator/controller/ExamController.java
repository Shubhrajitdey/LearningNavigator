package com.crio.LearningNavigator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.LearningNavigator.dto.ExamDTO;
import com.crio.LearningNavigator.dto.RegisterStudentInExamDTO;
import com.crio.LearningNavigator.service.ExamService;

@RestController
public class ExamController {
    private static final String URL_PATH = "LMS/exams";
    
    @Autowired
    private ExamService examService;
    
    @GetMapping(URL_PATH)
    public ResponseEntity<List<ExamDTO>> getAllExams() {
        return new ResponseEntity<>(examService.getExams(), HttpStatus.OK);
    }

    @GetMapping(URL_PATH + "/{examId}")
    public ResponseEntity<ExamDTO> getExam(@PathVariable("examId") Long examId) {
        return new ResponseEntity<>(examService.getExamById(examId), HttpStatus.OK);
    }

    @PostMapping(URL_PATH + "/subject/{subjectId}")
    public ResponseEntity<ExamDTO> addExam(@PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(examService.addExam(subjectId), HttpStatus.CREATED);
    }

    @PostMapping(URL_PATH + "/{examId}")
    public ResponseEntity<ExamDTO> registerStudentInExam(@PathVariable("examId") Long examId, @RequestBody RegisterStudentInExamDTO studentInExamDTO) {
        return new ResponseEntity<>(examService.registerStudent(examId, studentInExamDTO), HttpStatus.OK);
    }

    @DeleteMapping(URL_PATH + "/{examId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("examId") Long examId) {
        // return new ResponseEntity<>(examService.deleteExam(examId), HttpStatus.NO_CONTENT);
        examService.deleteExam(examId);
        return ResponseEntity.noContent().build();
    }
}
