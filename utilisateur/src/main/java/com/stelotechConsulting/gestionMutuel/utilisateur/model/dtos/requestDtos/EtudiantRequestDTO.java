package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;
import java.util.Date;

@Data
public class EtudiantRequestDTO {
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String sexe;
    private Long niveauId; // Ajout de l'id du niveau
    private String email; // Ajout de l'email pour la création du compte utilisateur
    // Le matricule sera généré automatiquement
}