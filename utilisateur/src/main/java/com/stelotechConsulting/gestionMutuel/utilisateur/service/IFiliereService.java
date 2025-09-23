package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FiliereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FiliereResponseDTO;
import java.util.List;

public interface IFiliereService {
    FiliereResponseDTO createFiliere(FiliereRequestDTO dto);
    FiliereResponseDTO getFiliereById(Long id);
    List<FiliereResponseDTO> getAllFilieres();
    FiliereResponseDTO updateFiliere(Long id, FiliereRequestDTO dto);
    void deleteFiliere(Long id);
    java.util.List<com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO> getSpecialitesByFiliereId(Long filiereId);
}
