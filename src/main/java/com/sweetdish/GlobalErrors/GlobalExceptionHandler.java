package com.sweetdish.GlobalErrors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Handle other general exceptions

    
    @ExceptionHandler(CommonError.class)
    public ResponseEntity<ResponseError> handleRuntimeException(CommonError ex, WebRequest request) {
    	ResponseError errorDetails = new ResponseError(
                LocalDateTime.now(),
                400, // Bad Request status
                ex.getMessage(), // Same message in the response body
                request.getDescription(false) // Path for reference
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleGlobalException(Exception ex, WebRequest request) {
    	ResponseError errorDetails = new ResponseError(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getLocalizedMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
