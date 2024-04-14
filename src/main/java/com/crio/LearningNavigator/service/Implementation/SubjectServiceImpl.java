package com.crio.LearningNavigator.service.Implementation;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crio.LearningNavigator.dto.EmptyBodyDTO;
import com.crio.LearningNavigator.dto.SubjectDTO;
import com.crio.LearningNavigator.dto.SubjectRequestDTO;
import com.crio.LearningNavigator.entity.Subject;
import com.crio.LearningNavigator.exception.ResourceNotFoundException;
import com.crio.LearningNavigator.repository.SubjectRepository;
import com.crio.LearningNavigator.service.SubjectService;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SubjectDTO addSubject(SubjectRequestDTO entity) {
        Subject newSubject = new Subject();
        newSubject.setName(entity.getSubjectName());
        Subject savedSubject = subjectRepository.save(newSubject);
        return modelMapper.map(savedSubject, SubjectDTO.class);
    }

    @Override
    public List<SubjectDTO> getAllSubjects() {
        List<Subject> Subjects = subjectRepository.findAll();
        List<SubjectDTO> SubjectDTOs = new ArrayList<>();
        Subjects.forEach(exam -> {
            SubjectDTOs.add(
                modelMapper.map(exam, SubjectDTO.class)
            );
        });
        return SubjectDTOs;
    }

    @Override
    public SubjectDTO getSubjectById(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        Subject subject = subjectRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(id)));
        return modelMapper.map(subject, SubjectDTO.class);        
    }
    @Override
    public EmptyBodyDTO delete(Long subjectId) {
        if(subjectId == null) {
            throw new IllegalArgumentException("Subject ID cannot be null");
        }
        Subject subject = subjectRepository.findById(subjectId)
            .orElseThrow(() -> new ResourceNotFoundException("Subject", "Subject Id", Long.toString(subjectId)));
        subjectRepository.delete(subject);
        return new EmptyBodyDTO();
    }
    
}
