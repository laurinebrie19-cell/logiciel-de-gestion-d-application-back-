package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;

@Data
public class ValiderBureauRequest {
    private Long fonctionBureauId;   // fonction à affecter
    private String actorEmail;       // email de l’utilisateur qui réalise l’action (extrait de ton contexte si tu en as)
}