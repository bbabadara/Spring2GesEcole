package com.ecole221.l3dev.gestion.scolarite.dto;

import com.ecole221.l3dev.gestion.scolarite.model.Filiere;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClasseResponse {
    private long id;
    private String code;
    private String libelle;
    private int fraisInscription;
    private int mensualite;
    private int autreFrais;
    private Filiere filiere;
}