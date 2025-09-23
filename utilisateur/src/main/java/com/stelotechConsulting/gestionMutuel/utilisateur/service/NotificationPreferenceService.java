package com.stelotechConsulting.gestionMutuel.utilisateur.service;



import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.NotificationPreference;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;

public interface NotificationPreferenceService {

    NotificationPreference getPreferenceByUser(Utilisateur utilisateur);
    NotificationPreference updatePreference(Utilisateur utilisateur, boolean receiveEmails);
}

