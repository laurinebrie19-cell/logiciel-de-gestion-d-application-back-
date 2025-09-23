package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CreateSpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO;

import java.util.List;

public interface ISpecialiteService {
    SpecialiteResponseDTO createSpecialite(CreateSpecialiteRequestDTO dto);
    SpecialiteResponseDTO getSpecialiteById(Long id);
    List<SpecialiteResponseDTO> getAllSpecialites();
    SpecialiteResponseDTO updateSpecialite(Long id, SpecialiteRequestDTO dto);
    void deleteSpecialite(Long id);
}
