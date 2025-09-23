package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.Data;

@Data
public class SpecialiteResponseDTO {
    private Long id;
    private String nom;
    private Long filiereId;
    private String filiereNom;
}
