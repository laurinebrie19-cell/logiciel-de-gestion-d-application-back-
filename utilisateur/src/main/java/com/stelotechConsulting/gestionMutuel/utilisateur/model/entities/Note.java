package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Entity
@Data
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double valeur;

    @ManyToOne(optional = false)
    private Matiere matiere;

    @ManyToOne(optional = false)
    private Etudiant etudiant;

    @ManyToOne(optional = false)
    private Utilisateur enseignant;

    @ManyToOne(optional = false)
    private Niveau niveau;

    @Column(nullable = false)
    private LocalDateTime dateAjout;
}
