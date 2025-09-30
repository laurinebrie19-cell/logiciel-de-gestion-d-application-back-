package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class NoteResponseDTO {
    private Long id;
    private Double valeur;
    private String matiereNom;
    private String etudiantNom;
    private String enseignantNom;
    private String niveauNom;
    private LocalDateTime dateAjout;
}
