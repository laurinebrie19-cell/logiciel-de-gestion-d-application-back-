package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;

@Data
public class SpecialiteRequestDTO {
    private String nom;
    private Long filiereId; // Pour lier à une filière
}
