package com.thanksbucket.common.controller;

import com.thanksbucket.common.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.thanksbucket")
public class ErrorControllerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidRequestHandler(MethodArgumentNotValidException e) {
        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError: e.getFieldErrors()){
            sb.append(String.format("%s : %s \n", fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .message(sb.toString())
                        .build());
    }
}
