package com.example.streamflix.rental.web;

import com.example.streamflix.rental.exception.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class RentalExceptionHandler {

    record ApiError(String message) {}

    @ExceptionHandler(DuplicateRentalException.class)
    public ResponseEntity<ApiError> duplicate(DuplicateRentalException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiError(ex.getMessage()));
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<ApiError> notFound(RentalNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(ex.getMessage()));
    }
}