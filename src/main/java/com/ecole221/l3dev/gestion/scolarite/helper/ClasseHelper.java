package com.ecole221.l3dev.gestion.scolarite.helper;

import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.dto.CreateClasseResponse;
import com.ecole221.l3dev.gestion.scolarite.dto.UpdateClasseRequest;
import com.ecole221.l3dev.gestion.scolarite.exception.ScolariteException;
import com.ecole221.l3dev.gestion.scolarite.exception.ScolariteNotFoundException;
import com.ecole221.l3dev.gestion.scolarite.mapper.ClasseMapper;
import com.ecole221.l3dev.gestion.scolarite.model.Classe;
import com.ecole221.l3dev.gestion.scolarite.service.IClasse;
import com.ecole221.l3dev.gestion.scolarite.service.IFiliere;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ClasseHelper {
    private final IClasse iClasse;
    private final ClasseMapper classeMapper;
    private final IFiliere iFiliere;

    public ClasseHelper(IClasse iClasse, ClasseMapper classeMapper, IFiliere iFiliere) {
        this.iClasse = iClasse;
        this.classeMapper = classeMapper;
        this.iFiliere = iFiliere;
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
        Classe classe = iClasse.save(classeMapper.createClasseRequestToClasseEntity(classeRequest));
         classe.getFiliere().setLibelle(iFiliere.findById(classe.getFiliere().getId()).getLibelle());

        return classeMapper.classeEntityToCreateClasseResponse(classe);
    }

    public List<CreateClasseResponse> findAll() {
        return iClasse.findAll().stream().map(classeMapper::classeEntityToCreateClasseResponse).toList();
    }

    public CreateClasseResponse findById(long id) {
        Classe classe = iClasse.findById(id);
        if (classe==null){
            throw new ScolariteNotFoundException("Classe avec id "+id+" introuvable");
        }
        return classeMapper.classeEntityToCreateClasseResponse(classe);
    }

    public CreateClasseResponse update(UpdateClasseRequest updateClasseRequest) {
        Classe classe=iClasse.findById(updateClasseRequest.getId());
        if(classe==null){
            throw new ScolariteNotFoundException("La classe avec l'id "+updateClasseRequest.getId()+" n'existe pas");
        }
        List<String> codes = iClasse.findClassesWithoutThisCode(updateClasseRequest.getCreateClasseRequest().getCode()).stream()
                .map(Classe::getCode).toList();
        if (codes.contains(updateClasseRequest.getCreateClasseRequest().getCode())){
            throw new ScolariteException("Une classe avec le code [ "+updateClasseRequest.getCreateClasseRequest().getCode()+" ] existe deja!");
        }
        List<String> libelles = iClasse.findClassesWithoutThisLibelle(updateClasseRequest.getCreateClasseRequest().getLibelle()).stream()
                .map(Classe::getLibelle).toList();
        if (libelles.contains(updateClasseRequest.getCreateClasseRequest().getLibelle())){
            throw new ScolariteException("Une classe avec le libelle [ "+updateClasseRequest.getCreateClasseRequest().getLibelle()+" ] existe deja!");
        }
        Classe classeToUpdate = classeMapper.createClasseRequestToClasseEntity(updateClasseRequest.getCreateClasseRequest());
        classeToUpdate.setId(classe.getId());
        classe = iClasse.save(classeToUpdate);
        return classeMapper.classeEntityToCreateClasseResponse(classe);
    }

    public ResponseEntity<?> delete(long id) {
        Classe classe = iClasse.findById(id);
        if(classe==null){
            throw new ScolariteNotFoundException("La classe avec l'id "+id+" n'existe pas");
        }
        iClasse.delete(classe);
        return ResponseEntity.ok("Classe supprimée");

    }
}
