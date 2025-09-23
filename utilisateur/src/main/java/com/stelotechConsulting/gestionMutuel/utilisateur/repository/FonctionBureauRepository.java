package com.stelotechConsulting.gestionMutuel.utilisateur.repository;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.FonctionBureau;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FonctionBureauRepository extends JpaRepository<FonctionBureau, Long> {
    Optional<FonctionBureau> findByLibelleIgnoreCase(String libelle);
    boolean existsByLibelle(String libelle);
    Optional<FonctionBureau> findByLibelle(String libelle);
}