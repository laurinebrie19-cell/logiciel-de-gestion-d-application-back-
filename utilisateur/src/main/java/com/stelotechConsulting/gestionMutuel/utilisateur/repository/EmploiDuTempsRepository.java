package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {
    List<EmploiDuTemps> findByNiveauId(Long niveauId);
    List<EmploiDuTemps> findByFiliereId(Long filiereId);
    List<EmploiDuTemps> findByFiliereIdAndNiveauId(Long filiereId, Long niveauId);
}