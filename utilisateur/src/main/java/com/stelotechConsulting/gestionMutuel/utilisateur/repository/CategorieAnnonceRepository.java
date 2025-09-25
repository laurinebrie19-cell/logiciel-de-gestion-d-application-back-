package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.CategorieAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieAnnonceRepository extends JpaRepository<CategorieAnnonce, Long> {
}

