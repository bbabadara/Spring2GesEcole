package com.ecole221.l3dev.gestion.scolarite.helper;

import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseResponse;
import com.ecole221.l3dev.gestion.scolarite.exception.ScolariteException;
import com.ecole221.l3dev.gestion.scolarite.mapper.ClasseMapper;
import com.ecole221.l3dev.gestion.scolarite.model.Classe;
import com.ecole221.l3dev.gestion.scolarite.service.IClasse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClasseHelper {
    private final IClasse iClasse;
    private final ClasseMapper classeMapper;

    public ClasseHelper(IClasse iClasse, ClasseMapper classeMapper) {
        this.iClasse = iClasse;
        this.classeMapper = classeMapper;
    }

    public CreateClasseResponse save(CreateClasseRequest classeRequest) {
        // Rechercher si le code de la classe existe
        if (iClasse.findByCode(classeRequest.getCode())!=null){
            throw new ScolariteException("Une classe avec le code [ "+classeRequest.getCode()+" ] existe deja!");
        }
         if (iClasse.findByLibelle(classeRequest.getLibelle())!=null){
            throw new ScolariteException("Une classe avec le libelle [ "+classeRequest.getLibelle()+" ] existe deja!");
        }
//        Classe classe = classeMapper.createClasseRequestToClasseEntity(classeRequest);
//         classe = iClasse.save(classe);
//         CreateClasseResponse createClasseResponse= classeMapper.classeEntityToCreateClasseResponse(classe) ;
//         return createClasseResponse;

        return classeMapper.classeEntityToCreateClasseResponse(
               iClasse.save(classeMapper.createClasseRequestToClasseEntity(classeRequest))
        );
    }

    public List<CreateClasseResponse> findAll() {
        return iClasse.findAll().stream().map(classeMapper::classeEntityToCreateClasseResponse).toList();
    }
}
