package com.stelotechConsulting.gestionMutuel.utilisateur.config;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.MatiereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Matiere;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Etudiant;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        // Configuration pour Matiere
        modelMapper.addMappings(new PropertyMap<MatiereRequestDTO, Matiere>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });

        // Configuration pour Etudiant
        modelMapper.addMappings(new PropertyMap<EtudiantRequestDTO, Etudiant>() {
            @Override
            protected void configure() {
                skip(destination.getId());
                skip(destination.getUtilisateur());
                skip(destination.getMatricule());
            }
        });

        modelMapper.getConfiguration()
            .setSkipNullEnabled(true)
            .setAmbiguityIgnored(true);

        return modelMapper;
    }
}