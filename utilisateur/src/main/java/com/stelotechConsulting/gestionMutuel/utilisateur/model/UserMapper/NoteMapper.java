package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NoteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NoteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Note;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Note toEntity(NoteRequestDTO dto) {
        Note note = new Note();
        note.setValeur(dto.getValeur());
        // Les relations (matiere, etudiant, enseignant, niveau) doivent être gérées dans le service
        return note;
    }

    public NoteResponseDTO toResponseDTO(Note note) {
        NoteResponseDTO dto = new NoteResponseDTO();
        dto.setId(note.getId());
        dto.setValeur(note.getValeur());
        dto.setMatiereNom(note.getMatiere().getNom());
        dto.setEtudiantNom(note.getEtudiant().getPrenom() + " " + note.getEtudiant().getNom());
        dto.setEnseignantNom(note.getEnseignant().getFirstName() + " " + note.getEnseignant().getLastName());
        dto.setNiveauNom(note.getNiveau().getNom());
        dto.setDateAjout(note.getDateAjout());
        return dto;
    }
}
