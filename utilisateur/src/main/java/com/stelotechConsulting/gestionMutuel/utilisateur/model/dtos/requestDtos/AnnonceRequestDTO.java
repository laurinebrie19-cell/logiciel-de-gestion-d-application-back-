package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class AnnonceRequestDTO {
    private String title;
    private String content;
    private String description;
    private String type;
    private String category;
    private LocalDate date;
    @Schema(type = "string", example = "09:30")
    private LocalTime time;
    private String location;
    private String imageUrl;
    private Boolean isActive;
    private Long typeAnnonceId;
    private Long categorieAnnonceId;
}
