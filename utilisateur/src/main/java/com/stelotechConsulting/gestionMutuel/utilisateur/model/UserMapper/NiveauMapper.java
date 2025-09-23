package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NiveauRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NiveauResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Niveau;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NiveauMapper {
    @Autowired
    private ModelMapper modelMapper;

    public NiveauResponseDTO convertToResponseDTO(Niveau niveau) {
        if (niveau == null) return null;
        return modelMapper.map(niveau, NiveauResponseDTO.class);
    }

    public Niveau convertToEntity(NiveauRequestDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Niveau.class);
    }
}
