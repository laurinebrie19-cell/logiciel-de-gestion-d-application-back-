package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.MatiereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.MatiereResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Matiere;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MatiereMapper {
    @Autowired
    private ModelMapper modelMapper;

    public MatiereResponseDTO convertToResponseDTO(Matiere matiere) {
        if (matiere == null) return null;
        return modelMapper.map(matiere, MatiereResponseDTO.class);
    }

    public Matiere convertToEntity(MatiereRequestDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Matiere.class);
    }
}
