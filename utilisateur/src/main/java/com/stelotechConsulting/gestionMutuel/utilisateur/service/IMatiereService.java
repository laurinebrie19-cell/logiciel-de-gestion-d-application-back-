package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.MatiereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.MatiereResponseDTO;
import java.util.List;

public interface IMatiereService {
    MatiereResponseDTO createMatiere(MatiereRequestDTO dto);
    MatiereResponseDTO getMatiereById(Long id);
    List<MatiereResponseDTO> getAllMatieres();
    MatiereResponseDTO updateMatiere(Long id, MatiereRequestDTO dto);
    void deleteMatiere(Long id);
    List<MatiereResponseDTO> getMatieresByNiveau(Long niveauId);
    List<MatiereResponseDTO> getMatieresBySpecialite(Long specialiteId);
    List<MatiereResponseDTO> getMatieresByNiveauAndSpecialite(Long niveauId, Long specialiteId);
    List<MatiereResponseDTO> getMatieresTroncCommunByNiveau(Long niveauId);
    List<MatiereResponseDTO> getMatieresSpecialiteByNiveau(Long niveauId, Long specialiteId);
}
