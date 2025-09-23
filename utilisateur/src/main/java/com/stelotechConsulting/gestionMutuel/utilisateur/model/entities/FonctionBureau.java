package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fonctions_bureau")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FonctionBureau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String libelle; // ex: Président, Trésorier

    private String description;
    private Boolean valid = true;
}