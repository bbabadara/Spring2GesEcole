package com.ecole221.l3dev.gestion.scolarite.service;

import com.ecole221.l3dev.gestion.scolarite.model.Classe;
import com.ecole221.l3dev.gestion.scolarite.repository.ClasseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClasseService implements IClasse {
    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    @Override
    public List<Classe> findAll() {
        return classeRepository.findAll();
    }

    @Override
    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    @Override
    public Classe findById(long id) {
        return classeRepository.findById(id).orElse(null);
    }

    @Override
    public Classe findByCode(String code) {
        return classeRepository.findByCode(code);
    }

    @Override
    public Classe findByLibelle(String libelle) {
        return classeRepository.findByLibelle(libelle);
    }

}
