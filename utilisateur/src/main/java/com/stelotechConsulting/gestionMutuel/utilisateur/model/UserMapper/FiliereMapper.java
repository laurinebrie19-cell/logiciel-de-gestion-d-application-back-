package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FiliereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FiliereResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Filiere;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FiliereMapper {
    @Autowired
    private ModelMapper modelMapper;

    public FiliereResponseDTO convertToResponseDTO(Filiere filiere) {
        if (filiere == null) return null;
        return modelMapper.map(filiere, FiliereResponseDTO.class);
    }

    public Filiere convertToEntity(FiliereRequestDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Filiere.class);
    }
}
