package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
    boolean existsByNom(String nom);
    boolean existsByNomAndFiliereId(String nom, Long filiereId);
    List<Specialite> findByFiliere_Id(Long filiereId);
}