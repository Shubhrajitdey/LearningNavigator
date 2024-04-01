package com.crio.LearningNavigator.service;

import java.util.List;

import com.crio.LearningNavigator.dto.SubjectDTO;
import com.crio.LearningNavigator.dto.SubjectRequestDTO;

public interface SubjectService {
    SubjectDTO addStudent(SubjectRequestDTO entity);
    List<SubjectDTO> getAllStudents();

    /**Implementation plan for future release
    SubjecttDTO getSubjectById(String id);
    EmptyBodyDTO delete(String subjectId);
    SubjectDTO updateStudent(String subjectId, SubjectDTO subjectDTO);
    **/
}
