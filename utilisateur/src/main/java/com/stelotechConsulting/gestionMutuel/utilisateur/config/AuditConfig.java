package com.stelotechConsulting.gestionMutuel.utilisateur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        // Valeur par défaut temporaire
        return () -> Optional.of("SuperAdmin");
    }
}
