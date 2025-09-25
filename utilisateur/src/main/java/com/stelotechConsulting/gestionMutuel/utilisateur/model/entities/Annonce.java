package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "annonces")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_annonce_id")
    private TypeAnnonce typeAnnonce;

    @ManyToOne
    @JoinColumn(name = "categorie_annonce_id")
    private CategorieAnnonce categorieAnnonce;

    private LocalDate date;
    private LocalTime time;
    private String location;
    private String imageUrl;
    private LocalDateTime publishedAt;
    private Boolean isActive;

    @PrePersist
    public void prePersist() {
        this.publishedAt = LocalDateTime.now();
        if (isActive == null) isActive = true;
    }
}
