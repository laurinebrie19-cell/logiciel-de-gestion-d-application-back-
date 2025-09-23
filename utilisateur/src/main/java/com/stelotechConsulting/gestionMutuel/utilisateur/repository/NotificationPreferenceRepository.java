package com.stelotechConsulting.gestionMutuel.utilisateur.repository;



import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.NotificationPreference;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NotificationPreferenceRepository extends JpaRepository<NotificationPreference, Long> {
    Optional<NotificationPreference> findByUtilisateur(Utilisateur utilisateur);
}

