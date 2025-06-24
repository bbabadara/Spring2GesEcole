package com.ecole221.l3dev.gestion.scolarite.exception;
//ceci est un test

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorDetail {
    private String field;
    private String message;
}
