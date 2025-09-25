package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.TypeAnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.TypeAnnonceResponseDTO;
import java.util.List;

public interface ITypeAnnonceService {
    TypeAnnonceResponseDTO createTypeAnnonce(TypeAnnonceRequestDTO dto);
    TypeAnnonceResponseDTO getTypeAnnonceById(Long id);
    List<TypeAnnonceResponseDTO> getAllTypeAnnonces();
    TypeAnnonceResponseDTO updateTypeAnnonce(Long id, TypeAnnonceRequestDTO dto);
    void deleteTypeAnnonce(Long id);
}

