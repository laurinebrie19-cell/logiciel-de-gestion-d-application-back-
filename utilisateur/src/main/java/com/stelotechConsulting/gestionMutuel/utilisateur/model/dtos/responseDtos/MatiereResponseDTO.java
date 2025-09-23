package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.Data;

@Data
public class MatiereResponseDTO {
    private Long id;
    private String nom;
    private String description;
    private Integer credit;
    private Long niveauId;
    private String niveauNom;
    private Long specialiteId;
    private String specialiteNom;
    private Boolean troncCommun;
}
