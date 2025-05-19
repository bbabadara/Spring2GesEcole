package com.ecole221.l3dev.gestion.scolarite.repository;
import com.ecole221.l3dev.gestion.scolarite.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    Classe findByCode(String code);

    Classe findByLibelle(String libelle);
}
