package com.ecole221.l3dev.gestion.scolarite.repository;

import com.ecole221.l3dev.gestion.scolarite.model.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere,Long> {
}
