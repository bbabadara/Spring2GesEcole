package com.ecole221.l3dev.gestion.scolarite.exception;
//ceci est un test

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class BadRequestException extends RuntimeException {
    private final HttpStatus status;
    private final List<FieldErrorDetail> errors;

    public BadRequestException(String message, List<FieldErrorDetail> errors) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.errors = errors;
    }
}
