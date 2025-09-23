package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notification_preferences")
public class NotificationPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "utilisateur_id", nullable = false, unique = true)
    private Utilisateur utilisateur;

    // Si l'utilisateur souhaite recevoir les emails de notification
    @Column(name = "receive_email_notifications", nullable = false)
    private boolean receiveEmailNotifications = true;

}
