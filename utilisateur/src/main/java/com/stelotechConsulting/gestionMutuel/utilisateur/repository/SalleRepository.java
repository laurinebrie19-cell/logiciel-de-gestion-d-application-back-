package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, Long> {
    boolean existsByNom(String nom);
}

