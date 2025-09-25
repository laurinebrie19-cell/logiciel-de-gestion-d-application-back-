package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.Data;
import java.time.LocalTime;

@Data
public class SeanceResponseDTO {
    private Long id;
    private String jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long matiereId;
    private String matiereNom;
    private Long enseignantId;
    private String enseignantNom;
    private Long salleId;
    private String salleNom;
}

