package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;

@Data
public class CreateSpecialiteRequestDTO {
    private String nom;
    private Long filiereId;
}
