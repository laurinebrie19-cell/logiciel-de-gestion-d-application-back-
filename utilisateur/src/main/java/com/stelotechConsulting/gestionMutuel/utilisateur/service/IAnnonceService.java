package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.AnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.AnnonceResponseDTO;
import java.util.List;

public interface IAnnonceService {
    AnnonceResponseDTO createAnnonce(AnnonceRequestDTO dto);
    AnnonceResponseDTO getAnnonceById(Long id);
    List<AnnonceResponseDTO> getAllAnnonces();
    AnnonceResponseDTO updateAnnonce(Long id, AnnonceRequestDTO dto);
    void deleteAnnonce(Long id);
    List<AnnonceResponseDTO> getAnnoncesByCategory(Long categorieAnnonceId);
    List<AnnonceResponseDTO> getAnnoncesByType(Long typeAnnonceId);
    List<AnnonceResponseDTO> getActiveAnnonces();
}
