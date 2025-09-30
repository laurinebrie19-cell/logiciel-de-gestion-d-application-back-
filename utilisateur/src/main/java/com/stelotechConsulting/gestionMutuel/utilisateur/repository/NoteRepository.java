package com.stelotechConsulting.gestionMutuel.utilisateur.repository;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Vous pouvez ajouter des méthodes personnalisées ici si nécessaire
}
