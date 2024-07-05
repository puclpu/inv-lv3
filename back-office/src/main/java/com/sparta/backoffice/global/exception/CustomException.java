package com.sparta.backoffice.global.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CustomException(ExceptionCode exceptionCode) {
        this(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
