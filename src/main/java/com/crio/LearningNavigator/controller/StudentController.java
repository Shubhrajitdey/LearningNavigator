package com.crio.LearningNavigator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.crio.LearningNavigator.dto.StudentDTO;
import com.crio.LearningNavigator.dto.StudentRequestDTO;
import com.crio.LearningNavigator.service.StudentService;

@RestController
public class StudentController {
    private static final String URL_PATH = "LMS/students";
    
    @Autowired
    private StudentService studentService;

    @PostMapping(URL_PATH)
    public ResponseEntity<StudentDTO> addStudent(@RequestBody StudentRequestDTO entity) {
        return new ResponseEntity<>(studentService.addStudent(entity) , HttpStatus.CREATED);
    }

    @GetMapping(URL_PATH)
    public ResponseEntity<List<StudentDTO>> getAllExams() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable("studentId") Long studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }
    
    @PutMapping(URL_PATH + "/{studentId}/subject/{subjectId}")
    public ResponseEntity<StudentDTO> enrollSubject(@PathVariable("studentId") Long studentId, @PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(studentService.enrollStudentToSubject(studentId, subjectId), HttpStatus.OK);
    }

    @PutMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable("studentId") Long studentId, @RequestBody StudentDTO entity) {
        return new ResponseEntity<>(studentService.updateStudent(studentId, entity), HttpStatus.OK);
    }
    
    @DeleteMapping(URL_PATH + "/{studentId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("studentId") Long studentId) {
        // return new ResponseEntity<>(studentService.delete(studentId), HttpStatus.NO_CONTENT);
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }
}
