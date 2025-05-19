package com.ecole221.l3dev.gestion.scolarite.service;

import com.ecole221.l3dev.gestion.scolarite.model.Filiere;
import com.ecole221.l3dev.gestion.scolarite.repository.FiliereRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FiliereService implements IFiliere{
    private final FiliereRepository filiereRepository;

    public FiliereService(FiliereRepository filiereRepository) {
        this.filiereRepository = filiereRepository;
    }

    @Override
    public List<Filiere> findAll() {
        return filiereRepository.findAll();
    }

    @Override
    public Filiere findById(long id) {
        return filiereRepository.findById(id).orElse(null);
    }
}
