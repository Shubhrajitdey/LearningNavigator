package com.crio.LearningNavigator.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;

@JsonSerialize
@Data
@AllArgsConstructor
public class SubjectRequestDTO {
    private String subjectName;
}
