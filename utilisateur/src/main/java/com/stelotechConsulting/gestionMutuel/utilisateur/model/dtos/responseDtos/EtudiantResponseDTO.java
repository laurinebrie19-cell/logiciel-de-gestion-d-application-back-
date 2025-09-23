package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.Data;
import java.util.Date;

@Data
public class EtudiantResponseDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String matricule;
    private String sexe;
    private String niveau;
}

