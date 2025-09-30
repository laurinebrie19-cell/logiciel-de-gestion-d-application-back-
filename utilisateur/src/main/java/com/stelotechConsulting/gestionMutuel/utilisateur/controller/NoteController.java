package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NoteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NoteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Note;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/ajouter")
    public ResponseEntity<NoteResponseDTO> ajouterNote(@RequestBody NoteRequestDTO noteRequestDTO) {
        NoteResponseDTO nouvelleNote = noteService.ajouterNote(noteRequestDTO);
        return ResponseEntity.ok(nouvelleNote);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<NoteResponseDTO>> getNotesParEtudiant(@PathVariable Long etudiantId) {
        List<NoteResponseDTO> notes = noteService.getNotesParEtudiant(etudiantId);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/matiere/{matiereId}/niveau/{niveauId}")
    public ResponseEntity<List<NoteResponseDTO>> getNotesParMatiereEtNiveau(@PathVariable Long matiereId, @PathVariable Long niveauId) {
        List<NoteResponseDTO> notes = noteService.getNotesParMatiereEtNiveau(matiereId, niveauId);
        return ResponseEntity.ok(notes);
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteResponseDTO> mettreAJourNote(@PathVariable Long noteId, @RequestBody NoteRequestDTO noteRequestDTO) {
        NoteResponseDTO noteMiseAJour = noteService.mettreAJourNote(noteId, noteRequestDTO);
        return ResponseEntity.ok(noteMiseAJour);
    }
}
