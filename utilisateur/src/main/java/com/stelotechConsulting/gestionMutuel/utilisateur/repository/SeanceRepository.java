package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Seance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SeanceRepository extends JpaRepository<Seance, Long> {
    List<Seance> findByEnseignantIdAndJour(Long enseignantId, String jour);
    List<Seance> findBySalleIdAndJour(Long salleId, String jour);
    List<Seance> findByEnseignantId(Long enseignantId);
    List<Seance> findByJour(String jour);
}
