package com.trasportManagement.transportservice.exception;

import org.springframework.http.HttpStatus;

public class TPMSCustomException extends RuntimeException{

    HttpStatus status;

    public TPMSCustomException(String message) {
        super(message);
    }

    public TPMSCustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public TPMSCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
