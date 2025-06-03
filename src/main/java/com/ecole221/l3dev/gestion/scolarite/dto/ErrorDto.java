package com.ecole221.l3dev.gestion.scolarite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorDto {
    private String code;
    private String message;
}
