package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EtudiantResponseDTO;
import java.util.List;

public interface IEtudiantService {
    EtudiantResponseDTO createEtudiant(EtudiantRequestDTO dto);
    EtudiantResponseDTO getEtudiantById(Long id);
    List<EtudiantResponseDTO> getAllEtudiants();
    EtudiantResponseDTO updateEtudiant(Long id, EtudiantRequestDTO dto);
    void deleteEtudiant(Long id);
}

