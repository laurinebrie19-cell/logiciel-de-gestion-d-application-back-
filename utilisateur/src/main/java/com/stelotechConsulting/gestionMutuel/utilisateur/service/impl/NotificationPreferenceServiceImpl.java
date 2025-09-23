package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.NotificationPreference;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.NotificationPreferenceRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.NotificationPreferenceService;
import org.springframework.stereotype.Service;

@Service
public class NotificationPreferenceServiceImpl implements NotificationPreferenceService {

    private final NotificationPreferenceRepository repo;

    public NotificationPreferenceServiceImpl(NotificationPreferenceRepository repo) {
        this.repo = repo;
    }

    @Override
    public NotificationPreference getPreferenceByUser(Utilisateur utilisateur) {
        return repo.findByUtilisateur(utilisateur)
                .orElseGet(() -> {
                    NotificationPreference pref = new NotificationPreference();
                    pref.setUtilisateur(utilisateur);
                    pref.setReceiveEmailNotifications(true);
                    return repo.save(pref);
                });
    }

    @Override
    public NotificationPreference updatePreference(Utilisateur utilisateur, boolean receiveEmails) {
        NotificationPreference pref = getPreferenceByUser(utilisateur);
        pref.setReceiveEmailNotifications(receiveEmails);
        return repo.save(pref);
    }
}
