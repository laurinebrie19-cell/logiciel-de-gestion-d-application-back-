package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateNaissance;

    @Column(unique = true, nullable = false)
    private String matricule;

    @Column(nullable = false)
    private String sexe;

    @Column(nullable = false)
    private String niveau;

    // Relation optionnelle avec Utilisateur (si besoin)
    // @OneToOne(fetch = FetchType.LAZY)
    // private Utilisateur utilisateur;
}

