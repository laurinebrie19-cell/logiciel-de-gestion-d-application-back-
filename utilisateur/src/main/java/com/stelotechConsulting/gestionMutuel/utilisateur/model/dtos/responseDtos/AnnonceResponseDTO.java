package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Data
public class AnnonceResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String description;
    private String type;
    private String category;
    private LocalDate date;
    private LocalTime time;
    private String location;
    private String imageUrl;
    private LocalDateTime publishedAt;
    private Boolean isActive;
    private Long typeAnnonceId;
    private String typeAnnonceNom;
    private Long categorieAnnonceId;
    private String categorieAnnonceNom;
}
