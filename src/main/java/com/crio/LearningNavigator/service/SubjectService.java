package com.crio.LearningNavigator.service;

import java.util.List;

import com.crio.LearningNavigator.dto.EmptyBodyDTO;
import com.crio.LearningNavigator.dto.SubjectDTO;
import com.crio.LearningNavigator.dto.SubjectRequestDTO;

public interface SubjectService {
    SubjectDTO addSubject(SubjectRequestDTO entity);
    List<SubjectDTO> getAllSubjects();
    SubjectDTO getSubjectById(Long id);
    EmptyBodyDTO delete(Long subjectId);
}
