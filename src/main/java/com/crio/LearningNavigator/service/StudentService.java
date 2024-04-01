package com.crio.LearningNavigator.service;

import java.util.List;
import com.crio.LearningNavigator.dto.StudentDTO;
import com.crio.LearningNavigator.dto.StudentRequestDTO;

public interface StudentService {
    StudentDTO addStudent(StudentRequestDTO entity);
    List<StudentDTO> getAllStudents();
    StudentDTO enrollStudentToSubject(String studentId, String subjectId);

    /**Implementation plan for future release
    StudentDTO getStudentById(Long id);
    EmptyBodyDTO delete(Long studentId);
    StudentDTO updateStudent(Long studentId, StudentDTO studentDto);
    **/
}
