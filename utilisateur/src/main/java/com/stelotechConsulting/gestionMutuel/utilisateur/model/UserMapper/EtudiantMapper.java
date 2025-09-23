package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EtudiantResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Etudiant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EtudiantResponseDTO convertToResponseDTO(Etudiant etudiant) {
        if (etudiant == null) return null;
        return modelMapper.map(etudiant, EtudiantResponseDTO.class);
    }

    public Etudiant convertToEntity(EtudiantRequestDTO dto) {
        if (dto == null) return null;
        return modelMapper.map(dto, Etudiant.class);
    }
}

