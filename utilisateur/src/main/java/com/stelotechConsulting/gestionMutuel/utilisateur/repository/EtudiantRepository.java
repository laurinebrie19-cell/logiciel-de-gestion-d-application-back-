package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    boolean existsByMatricule(String matricule);

    List<Etudiant> findByNiveau_Id(Long niveauId);
}
