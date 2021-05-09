package com.trasportManagement.transportservice.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TPMSCustomExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "Malformed JSON request. Check all the input fields";

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                error,
                badRequest
        );
        return new ResponseEntity<Object>(TPMSCutomExceptionModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {TPMSCustomException.class})
    public ResponseEntity<Object> handleTPMSCustomException(TPMSCustomException ex){
        HttpStatus notFound;

//        String errorMsg = d.getCause().getMessage();
//
//        notFound = errorMsg.contains("No data found") ? HttpStatus.NOT_FOUND : HttpStatus.BAD_REQUEST;

        notFound = ex.getStatus();

        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                ex.getMessage(),
                notFound
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, notFound);
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(NullPointerException ex){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                ex.getMessage(),
                notFound
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, notFound);
    }

    @ExceptionHandler(value = {EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        String errorMsg = "Data Not found";
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                errorMsg,
                notFound
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, notFound);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                ex.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, badRequest);
    }

    @ExceptionHandler(value = {DataAccessException.class})
    public ResponseEntity<Object> handleDataAccessException(DataAccessException ex){
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                ex.getMessage(),
                internalServerError
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, internalServerError);
    }

    @ExceptionHandler({ DuplicateKeyException.class })
    private ResponseEntity<Object> duplicateKeyExceptionException(DuplicateKeyException ex) {
        String errorMsg = ex.getCause().getMessage();
        String fieldName = errorMsg.substring(errorMsg.lastIndexOf(" ")+1);
        errorMsg = errorMsg.contains("Duplicate") ? fieldName + " field requires a unique value" : errorMsg;

        HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                errorMsg,
                badRequest
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, badRequest);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    private ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex) {
        String errorMsg = ex.getCause().getMessage();
//        String fieldName = errorMsg.substring(errorMsg.indexOf("'"),errorMsg.lastIndexOf("'")+1);
        errorMsg = errorMsg.contains("foreign key constraint fails") ? "Something wrong with given child data" : errorMsg;
//
        HttpStatus badRequest = HttpStatus.NOT_ACCEPTABLE;
        TPMSCutomExceptionModel TPMSCutomExceptionModel = new TPMSCutomExceptionModel(
                errorMsg,
                badRequest
        );
        return new ResponseEntity<>(TPMSCutomExceptionModel, badRequest);
    }

}
