package com.ecole221.l3dev.gestion.scolarite.service;

import com.ecole221.l3dev.gestion.scolarite.model.Classe;

import java.util.List;

public interface IClasse {
    List<Classe> findAll();

    Classe save(Classe classe);

    Classe findById(long id);

    Classe findByCode(String code);

    Classe findByLibelle(String libelle);

    List<Classe> findClassesWithoutThisCode(String code);
    List<Classe> findClassesWithoutThisLibelle(String libelle);

    void delete(Classe classe);
}
