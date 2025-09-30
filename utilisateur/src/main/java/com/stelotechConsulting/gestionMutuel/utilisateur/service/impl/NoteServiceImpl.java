package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.NoteMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NoteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NoteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Note;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.*;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private NiveauRepository niveauRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public NoteResponseDTO ajouterNote(NoteRequestDTO noteRequestDTO) {
        Note note = noteMapper.toEntity(noteRequestDTO);
        note.setMatiere(matiereRepository.findById(noteRequestDTO.getMatiereId()).orElseThrow(() -> new RuntimeException("Matière introuvable")));
        note.setEtudiant(etudiantRepository.findById(noteRequestDTO.getEtudiantId()).orElseThrow(() -> new RuntimeException("Étudiant introuvable")));
        note.setEnseignant(userRepository.findById(noteRequestDTO.getEnseignantId()).orElseThrow(() -> new RuntimeException("Enseignant introuvable")));
        note.setNiveau(niveauRepository.findById(noteRequestDTO.getNiveauId()).orElseThrow(() -> new RuntimeException("Niveau introuvable")));
        note.setDateAjout(LocalDateTime.now());
        Note savedNote = noteRepository.save(note);
        return noteMapper.toResponseDTO(savedNote);
    }

    @Override
    public List<NoteResponseDTO> getNotesParEtudiant(Long etudiantId) {
        return noteRepository.findAll().stream()
                .filter(note -> note.getEtudiant().getId().equals(etudiantId))
                .map(noteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<NoteResponseDTO> getNotesParMatiereEtNiveau(Long matiereId, Long niveauId) {
        return noteRepository.findAll().stream()
                .filter(note -> note.getMatiere().getId().equals(matiereId) && note.getNiveau().getId().equals(niveauId))
                .map(noteMapper::toResponseDTO)
                .toList();
    }

    @Override
    public NoteResponseDTO mettreAJourNote(Long noteId, NoteRequestDTO noteRequestDTO) {
        Note note = noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note introuvable"));
        note.setValeur(noteRequestDTO.getValeur());
        note.setMatiere(matiereRepository.findById(noteRequestDTO.getMatiereId()).orElseThrow(() -> new RuntimeException("Matière introuvable")));
        note.setEtudiant(etudiantRepository.findById(noteRequestDTO.getEtudiantId()).orElseThrow(() -> new RuntimeException("Étudiant introuvable")));
        note.setEnseignant(userRepository.findById(noteRequestDTO.getEnseignantId()).orElseThrow(() -> new RuntimeException("Enseignant introuvable")));
        note.setNiveau(niveauRepository.findById(noteRequestDTO.getNiveauId()).orElseThrow(() -> new RuntimeException("Niveau introuvable")));
        Note updatedNote = noteRepository.save(note);
        return noteMapper.toResponseDTO(updatedNote);
    }
}
