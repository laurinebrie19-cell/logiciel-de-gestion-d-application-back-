package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CategorieAnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.CategorieAnnonceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.CategorieAnnonce;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.CategorieAnnonceRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ICategorieAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategorieAnnonceServiceImpl implements ICategorieAnnonceService {
    @Autowired
    private CategorieAnnonceRepository categorieAnnonceRepository;

    @Override
    public CategorieAnnonceResponseDTO createCategorieAnnonce(CategorieAnnonceRequestDTO dto) {
        CategorieAnnonce cat = CategorieAnnonce.builder()
            .nom(dto.getNom())
            .description(dto.getDescription())
            .build();
        cat = categorieAnnonceRepository.save(cat);
        return toResponseDTO(cat);
    }

    @Override
    public CategorieAnnonceResponseDTO getCategorieAnnonceById(Long id) {
        CategorieAnnonce cat = categorieAnnonceRepository.findById(id).orElse(null);
        return cat != null ? toResponseDTO(cat) : null;
    }

    @Override
    public List<CategorieAnnonceResponseDTO> getAllCategorieAnnonces() {
        return categorieAnnonceRepository.findAll().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public CategorieAnnonceResponseDTO updateCategorieAnnonce(Long id, CategorieAnnonceRequestDTO dto) {
        CategorieAnnonce cat = categorieAnnonceRepository.findById(id).orElse(null);
        if (cat == null) return null;
        cat.setNom(dto.getNom());
        cat.setDescription(dto.getDescription());
        cat = categorieAnnonceRepository.save(cat);
        return toResponseDTO(cat);
    }

    @Override
    public void deleteCategorieAnnonce(Long id) {
        categorieAnnonceRepository.deleteById(id);
    }

    private CategorieAnnonceResponseDTO toResponseDTO(CategorieAnnonce cat) {
        CategorieAnnonceResponseDTO dto = new CategorieAnnonceResponseDTO();
        dto.setId(cat.getId());
        dto.setNom(cat.getNom());
        dto.setDescription(cat.getDescription());
        return dto;
    }
}

