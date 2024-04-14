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

import com.crio.LearningNavigator.dto.SubjectDTO;
import com.crio.LearningNavigator.dto.SubjectRequestDTO;
import com.crio.LearningNavigator.service.SubjectService;

@RestController
public class SubjectController {
    private static final String URL_PATH = "LMS/subjects";
    @Autowired
    private SubjectService subjectService;

    @GetMapping(URL_PATH)
    public ResponseEntity<List<SubjectDTO>> getAllSubjects() {
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @GetMapping(URL_PATH + "/{subjectId}")
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable("subjectId") Long subjectId) {
        return new ResponseEntity<>(subjectService.getSubjectById(subjectId), HttpStatus.OK);
    }

    @PostMapping(URL_PATH)
    public ResponseEntity<SubjectDTO> addStudent(@RequestBody SubjectRequestDTO entity) {
        return new ResponseEntity<>(subjectService.addSubject(entity) , HttpStatus.CREATED);
    }

    @DeleteMapping(URL_PATH + "/{subjectId}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("subjectId") Long subjectId) {
        // return new ResponseEntity<>(studentService.delete(studentId), HttpStatus.NO_CONTENT);
        subjectService.delete(subjectId);
        return ResponseEntity.noContent().build();
    }
}
