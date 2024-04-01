package com.crio.LearningNavigator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.crio.LearningNavigator.dto.ExceptionApiResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionApiResponseDTO> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message = ex.getMessage();
        ExceptionApiResponseDTO apiResponse = new ExceptionApiResponseDTO(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<ExceptionApiResponseDTO> illegalStateExceptionHandler(Exception ex){
        String message = ex.getMessage();
        ExceptionApiResponseDTO apiResponse = new ExceptionApiResponseDTO(message);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
