package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NiveauRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NiveauResponseDTO;
import java.util.List;

public interface INiveauService {
    NiveauResponseDTO createNiveau(NiveauRequestDTO dto);
    NiveauResponseDTO getNiveauById(Long id);
    List<NiveauResponseDTO> getAllNiveaux();
    NiveauResponseDTO updateNiveau(Long id, NiveauRequestDTO dto);
    void deleteNiveau(Long id);
}
