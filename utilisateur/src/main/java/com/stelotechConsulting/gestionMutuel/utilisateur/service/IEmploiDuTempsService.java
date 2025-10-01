package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EmploiDuTempsRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EmploiDuTempsResponseDTO;
import java.util.List;

public interface IEmploiDuTempsService {
    EmploiDuTempsResponseDTO createEmploiDuTemps(EmploiDuTempsRequestDTO dto);
    EmploiDuTempsResponseDTO getEmploiDuTempsById(Long id);
    List<EmploiDuTempsResponseDTO> getEmploisDuTempsByNiveau(Long niveauId);
    List<EmploiDuTempsResponseDTO> getEmploisDuTempsByFiliere(Long filiereId);
    List<EmploiDuTempsResponseDTO> getEmploisDuTempsByFiliereAndNiveau(Long filiereId, Long niveauId);
    EmploiDuTempsResponseDTO updateEmploiDuTemps(Long id, EmploiDuTempsRequestDTO dto);
    void deleteEmploiDuTemps(Long id);
    List<EmploiDuTempsResponseDTO> getAllEmploisDuTemps();
}