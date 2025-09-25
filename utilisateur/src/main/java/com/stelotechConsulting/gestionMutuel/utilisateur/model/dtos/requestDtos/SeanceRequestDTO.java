package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;
import java.time.LocalTime;

@Data
public class SeanceRequestDTO {
    private Long id;
    private String jour;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Long matiereId;
    private Long enseignantId;
    private Long salleId;
}
