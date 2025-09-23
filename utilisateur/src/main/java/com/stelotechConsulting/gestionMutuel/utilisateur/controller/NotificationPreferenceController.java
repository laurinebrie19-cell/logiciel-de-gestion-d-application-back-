package com.stelotechConsulting.gestionMutuel.utilisateur.controller;



import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.NotificationPreference;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.NotificationPreferenceService;
 // service pour récupérer l'utilisateur
import com.stelotechConsulting.gestionMutuel.utilisateur.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications/preferences")
public class NotificationPreferenceController {

    private final NotificationPreferenceService notificationPreferenceService;
    private final UserService utilisateurService;

    public NotificationPreferenceController(NotificationPreferenceService notificationPreferenceService,
                                            UserService utilisateurService) {
        this.notificationPreferenceService = notificationPreferenceService;
        this.utilisateurService = utilisateurService;
    }

    // Récupérer la préférence de notification d'un utilisateur par son id utilisateur
    @GetMapping("/{userId}")
    public ResponseEntity<NotificationPreference> getPreference(@PathVariable Long userId) {
        Utilisateur utilisateur = utilisateurService.findEntityById(userId);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }
        NotificationPreference preference = notificationPreferenceService.getPreferenceByUser(utilisateur);
        return ResponseEntity.ok(preference);
    }


    // Mettre à jour la préférence de notification d'un utilisateur
    @PutMapping("/{userId}")
    public ResponseEntity<NotificationPreference> updatePreference(
            @PathVariable Long userId,
            @RequestParam boolean receiveEmailNotifications) {

        Utilisateur utilisateur = utilisateurService.findEntityById(userId);
        if (utilisateur == null) {
            return ResponseEntity.notFound().build();
        }

        NotificationPreference updatedPreference = notificationPreferenceService.updatePreference(utilisateur, receiveEmailNotifications);
        return ResponseEntity.ok(updatedPreference);
    }
}

