package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SalleRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SalleResponseDTO;

import java.util.List;

public interface ISalleService {
    SalleResponseDTO createSalle(SalleRequestDTO dto);
    SalleResponseDTO getSalleById(Long id);
    List<SalleResponseDTO> getAllSalles();
    SalleResponseDTO updateSalle(Long id, SalleRequestDTO dto);
    void deleteSalle(Long id);
}

