package com.ecole221.l3dev.gestion.scolarite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClasseRequest {
    private String code;
    private String libelle;
    private int fraisInscription;
    private int mensualite;
    private int autreFrais;
    private long filiereId;
}
