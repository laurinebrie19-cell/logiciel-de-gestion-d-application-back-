package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmploiDuTempsRequestDTO {
    private String titre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Long niveauId;
    private Long filiereId;
    private List<SeanceRequestDTO> seances;
}
