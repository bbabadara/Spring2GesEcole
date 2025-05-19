package com.ecole221.l3dev.gestion.scolarite.service;

import com.ecole221.l3dev.gestion.scolarite.model.Filiere;

import java.util.List;

public interface IFiliere {
     List<Filiere> findAll();
     Filiere findById(long id);

}
