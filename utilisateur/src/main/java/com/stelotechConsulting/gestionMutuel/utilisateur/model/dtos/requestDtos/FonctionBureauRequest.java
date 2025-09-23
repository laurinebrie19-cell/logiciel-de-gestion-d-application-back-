package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FonctionBureauRequest {
    private String libelle;
    private String description;
}