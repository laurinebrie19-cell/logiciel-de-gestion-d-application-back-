package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Utilisateur, Long> {
    boolean existsByEmail(String email); // Ajoutez cette méthode

    Optional<Utilisateur> findByEmail(String email);
    Optional<Utilisateur> findByEmailAndStatus(String email, String status);

}