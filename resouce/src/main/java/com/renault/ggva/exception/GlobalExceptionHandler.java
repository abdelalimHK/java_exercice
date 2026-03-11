package com.renault.ggva.exception;

import com.renault.ggva.domain.exception.DomainException;
import com.renault.ggva.domain.exception.garage.GarageCapacityExceededException;
import com.renault.ggva.domain.exception.garage.GarageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GarageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleGarageNotFound(GarageNotFoundException ex) {
        return new ErrorResponse("GARAGE_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(GarageCapacityExceededException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleCapacityExceeded(GarageCapacityExceededException ex) {
        return new ErrorResponse("GARAGE_CAPACITY_EXCEEDED", ex.getMessage());
    }

    @ExceptionHandler(DomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleDomainException(DomainException ex) {
        return new ErrorResponse("DOMAIN_ERROR", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return new ErrorResponse("VALIDATION_ERROR", message);
    }
}

