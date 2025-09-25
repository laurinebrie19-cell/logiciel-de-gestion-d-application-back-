package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CategorieAnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.CategorieAnnonceResponseDTO;
import java.util.List;

public interface ICategorieAnnonceService {
    CategorieAnnonceResponseDTO createCategorieAnnonce(CategorieAnnonceRequestDTO dto);
    CategorieAnnonceResponseDTO getCategorieAnnonceById(Long id);
    List<CategorieAnnonceResponseDTO> getAllCategorieAnnonces();
    CategorieAnnonceResponseDTO updateCategorieAnnonce(Long id, CategorieAnnonceRequestDTO dto);
    void deleteCategorieAnnonce(Long id);
}

