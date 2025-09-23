package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;
import java.util.Date;

@Data
public class EtudiantRequestDTO {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String sexe;
    private String niveau;
    // Le matricule sera généré automatiquement
}

