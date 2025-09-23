package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FonctionBureauResponse {
    private Long id;
    private String libelle;
    private String description;
    private Boolean valid;
}