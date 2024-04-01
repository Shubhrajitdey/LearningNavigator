package com.crio.LearningNavigator.exception;

public class ResourceNotFoundException extends RuntimeException{
    String resourseName;
    String fieldName;
    String fieldValue;
    public ResourceNotFoundException(String resourseName, String fieldName, String fieldValue) {
        super(String.format("%s not found with %s: %s", resourseName, fieldName, fieldValue));
        this.resourseName = resourseName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
