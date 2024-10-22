package com.study.studycase.controller;

import com.study.studycase.dto.response.BaseResponse;
import com.study.studycase.exception.ExceptionImage;
import com.study.studycase.exception.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandlers {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errors.add(errorMessage);
        });

        if (!errors.isEmpty()) {
            String errorMessage = String.join(", ", errors);
            BaseResponse<String> errorResponse = BaseResponse.failure(
                    HttpStatus.BAD_REQUEST.value(),
                    errorMessage
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }

        return ResponseEntity.badRequest().body(null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<BaseResponse<String>> constraintViolationException(ConstraintViolationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.failure(HttpStatus.BAD_REQUEST.value(), exception.getMessage()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<BaseResponse<String>> apiException(ResponseStatusException exception) {
        return ResponseEntity.status(exception.getRawStatusCode())
                .body(BaseResponse.failure(exception.getRawStatusCode(), exception.getReason()));
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<BaseResponse<String>> handleInvalidFormatException(InvalidFormatException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.failure(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(ExceptionImage.class)
    public ResponseEntity<BaseResponse<String>> handleInvalidFormatException(ExceptionImage ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(BaseResponse.failure(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
