package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmploiDuTempsResponseDTO {
    private Long id;
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long niveauId;
    private List<SeanceResponseDTO> seances;
}

