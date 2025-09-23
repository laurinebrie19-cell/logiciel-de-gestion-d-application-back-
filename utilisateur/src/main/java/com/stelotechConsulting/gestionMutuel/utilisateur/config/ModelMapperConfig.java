package com.stelotechConsulting.gestionMutuel.utilisateur.config;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.MatiereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Matiere;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Ignore le champ id lors du mapping de MatiereRequestDTO vers Matiere
        modelMapper.addMappings(new PropertyMap<MatiereRequestDTO, Matiere>() {
            @Override
            protected void configure() {
                skip(destination.getId());
            }
        });
        return modelMapper;
    }
}