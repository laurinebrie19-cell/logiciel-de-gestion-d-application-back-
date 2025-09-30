package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;

@Data
public class NoteRequestDTO {
    private Double valeur;
    private Long matiereId;
    private Long etudiantId;
    private Long enseignantId;
    private Long niveauId;
}
