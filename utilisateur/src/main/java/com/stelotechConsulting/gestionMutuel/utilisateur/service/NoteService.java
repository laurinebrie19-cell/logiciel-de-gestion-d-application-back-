package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NoteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NoteResponseDTO;
import java.util.List;

public interface NoteService {
    NoteResponseDTO ajouterNote(NoteRequestDTO noteRequestDTO);
    List<NoteResponseDTO> getNotesParEtudiant(Long etudiantId);
    List<NoteResponseDTO> getNotesParMatiereEtNiveau(Long matiereId, Long niveauId);
    NoteResponseDTO mettreAJourNote(Long noteId, NoteRequestDTO noteRequestDTO);
}