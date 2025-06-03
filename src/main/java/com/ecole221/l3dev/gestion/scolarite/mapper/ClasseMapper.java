package com.ecole221.l3dev.gestion.scolarite.mapper;

import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseResponse;
//import com.ecole221.l3dev.gestion.scolarite.dto.FiliereDto;
import com.ecole221.l3dev.gestion.scolarite.model.Classe;
import com.ecole221.l3dev.gestion.scolarite.model.Filiere;
import org.springframework.stereotype.Component;

@Component
public class ClasseMapper {
    public Classe createClasseRequestToClasseEntity(CreateClasseRequest createClasseRequest){
        return Classe.builder()
                .code(createClasseRequest.getCode())
                .libelle(createClasseRequest.getLibelle())
                .autreFrais(createClasseRequest.getAutreFrais())
                .fraisInscription(createClasseRequest.getFraisInscription())
                .mensualite(createClasseRequest.getMensualite())
                .filiere(Filiere.builder()
                        .id(createClasseRequest.getFiliereId())
                        .build())
                .build();

    }

   public CreateClasseResponse classeEntityToCreateClasseResponse(Classe classe){
        return CreateClasseResponse.builder()
                .id(classe.getId())
                .code(classe.getCode())
                .libelle(classe.getLibelle())
                .autreFrais(classe.getAutreFrais())
                .fraisInscription(classe.getFraisInscription())
                .mensualite(classe.getMensualite())
                .filiere(Filiere.builder()
                        .id(classe.getFiliere().getId())
                        .libelle(classe.getFiliere().getLibelle())
                        .build())
                .build();
   }
}
