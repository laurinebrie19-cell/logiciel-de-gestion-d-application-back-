package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;

@Data
public class MatiereRequestDTO {
    private String nom;
    private String description;
    private Long filiereId;
    private Long niveauId;
    private Integer credit;
    private Boolean troncCommun;
    private Long specialiteId;
}