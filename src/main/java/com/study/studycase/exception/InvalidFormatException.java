package com.study.studycase.exception;


public class InvalidFormatException extends RuntimeException{
    private String message;

    public InvalidFormatException(String message) {
        super(message);
        this.message = message;
    }
}
