package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {
    boolean existsByNom(String nom);
    List<Matiere> findByNiveauId(Long niveauId);
    List<Matiere> findBySpecialiteId(Long specialiteId);
    List<Matiere> findByNiveauIdAndSpecialiteId(Long niveauId, Long specialiteId);
    List<Matiere> findByNiveauIdAndTroncCommunTrue(Long niveauId);
    List<Matiere> findByNiveauIdAndSpecialiteIdAndTroncCommunFalse(Long niveauId, Long specialiteId);
}
