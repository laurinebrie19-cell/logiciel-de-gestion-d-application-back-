package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.TypeAnnonce;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeAnnonceRepository extends JpaRepository<TypeAnnonce, Long> {
}

