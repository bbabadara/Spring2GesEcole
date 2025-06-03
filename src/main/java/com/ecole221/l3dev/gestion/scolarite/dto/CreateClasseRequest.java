package com.ecole221.l3dev.gestion.scolarite.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClasseRequest {
    @NotBlank(message = "Le code est obligatoire")
    private String code;
    @NotBlank(message = "Le libelle est obligatoire")
    private String libelle;
    @NotNull(message = "Frais d'incription obligatoire")
    @Min(value =20000,message = "minimum 20000 fr")
    private int fraisInscription;
    @NotNull(message = "Mensualite obligatoire")
    @Min(value =25000,message = "minimum 25000 fr")
    private int mensualite;
    @NotNull(message = "Autre frais obligatoire")
    @Min(value =15000,message = "minimum 15000 fr")
    private int autreFrais;
    @NotNull(message = "Filiere obligatoire")
    private long filiereId;
}
