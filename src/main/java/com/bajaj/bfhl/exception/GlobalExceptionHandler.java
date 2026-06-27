package com.bajaj.bfhl.exception;

import com.bajaj.bfhl.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> handleAllExceptions(Exception ex) {
        BfhlResponse response = BfhlResponse.builder()
                .isSuccess(false)
                .build();
        // Standard expected status is 200, but for invalid format we return 400 Bad Request
        // We will just return 400 for generic exceptions.
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
