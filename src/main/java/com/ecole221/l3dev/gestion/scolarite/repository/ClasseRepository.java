package com.ecole221.l3dev.gestion.scolarite.repository;
import com.ecole221.l3dev.gestion.scolarite.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe,Long> {
    Classe findByCode(String code);
    Classe findByLibelle(String libelle);
    @Query(value = "SELECT c FROM Classe c WHERE c.code != :code")
    List<Classe> findClassesWithoutThisCode(String code);
    @Query(value = "SELECT c FROM Classe c WHERE c.libelle != :libelle")
    List<Classe> findClassesWithoutThisLibelle(String libelle);
}
