package com.trasportManagement.transportservice.exception;

import org.springframework.http.HttpStatus;

public class TPMSCutomExceptionModel {
    private final String message;
    private final HttpStatus httpStatus;

    public TPMSCutomExceptionModel(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
