package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.AnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.AnnonceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Annonce;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.TypeAnnonce;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.CategorieAnnonce;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.AnnonceRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.TypeAnnonceRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.CategorieAnnonceRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnnonceServiceImpl implements IAnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired private TypeAnnonceRepository typeAnnonceRepository;
    @Autowired private CategorieAnnonceRepository categorieAnnonceRepository;

    @Override
    public AnnonceResponseDTO createAnnonce(AnnonceRequestDTO dto) {
        TypeAnnonce typeAnnonce = typeAnnonceRepository.findById(dto.getTypeAnnonceId())
            .orElseThrow(() -> new IllegalArgumentException("Type d'annonce non trouvé"));
        CategorieAnnonce categorieAnnonce = categorieAnnonceRepository.findById(dto.getCategorieAnnonceId())
            .orElseThrow(() -> new IllegalArgumentException("Catégorie d'annonce non trouvée"));
        Annonce annonce = Annonce.builder()
            .title(dto.getTitle())
            .content(dto.getContent())
            .description(dto.getDescription())
            .typeAnnonce(typeAnnonce)
            .categorieAnnonce(categorieAnnonce)
            .date(dto.getDate())
            .time(dto.getTime())
            .location(dto.getLocation())
            .imageUrl(dto.getImageUrl())
            .isActive(dto.getIsActive() != null ? dto.getIsActive() : true)
            .build();
        annonce = annonceRepository.save(annonce);
        return toResponseDTO(annonce);
    }

    @Override
    public AnnonceResponseDTO getAnnonceById(Long id) {
        Annonce annonce = annonceRepository.findById(id).orElse(null);
        return annonce != null ? toResponseDTO(annonce) : null;
    }

    @Override
    public List<AnnonceResponseDTO> getAllAnnonces() {
        return annonceRepository.findAll().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public AnnonceResponseDTO updateAnnonce(Long id, AnnonceRequestDTO dto) {
        Annonce annonce = annonceRepository.findById(id).orElse(null);
        if (annonce == null) return null;
        TypeAnnonce typeAnnonce = typeAnnonceRepository.findById(dto.getTypeAnnonceId())
            .orElseThrow(() -> new IllegalArgumentException("Type d'annonce non trouvé"));
        CategorieAnnonce categorieAnnonce = categorieAnnonceRepository.findById(dto.getCategorieAnnonceId())
            .orElseThrow(() -> new IllegalArgumentException("Catégorie d'annonce non trouvée"));
        annonce.setTitle(dto.getTitle());
        annonce.setContent(dto.getContent());
        annonce.setDescription(dto.getDescription());
        annonce.setTypeAnnonce(typeAnnonce);
        annonce.setCategorieAnnonce(categorieAnnonce);
        annonce.setDate(dto.getDate());
        annonce.setTime(dto.getTime());
        annonce.setLocation(dto.getLocation());
        annonce.setImageUrl(dto.getImageUrl());
        annonce.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : annonce.getIsActive());
        annonce = annonceRepository.save(annonce);
        return toResponseDTO(annonce);
    }

    @Override
    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }

    @Override
    public List<AnnonceResponseDTO> getAnnoncesByCategory(Long categorieAnnonceId) {
        return annonceRepository.findByCategorieAnnonce_Id(categorieAnnonceId).stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceResponseDTO> getAnnoncesByType(Long typeAnnonceId) {
        return annonceRepository.findByTypeAnnonce_Id(typeAnnonceId).stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AnnonceResponseDTO> getActiveAnnonces() {
        return annonceRepository.findByIsActiveTrue().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    private AnnonceResponseDTO toResponseDTO(Annonce annonce) {
        AnnonceResponseDTO dto = new AnnonceResponseDTO();
        dto.setId(annonce.getId());
        dto.setTitle(annonce.getTitle());
        dto.setContent(annonce.getContent());
        dto.setDescription(annonce.getDescription());
        dto.setDate(annonce.getDate());
        dto.setTime(annonce.getTime());
        dto.setLocation(annonce.getLocation());
        dto.setImageUrl(annonce.getImageUrl());
        dto.setPublishedAt(annonce.getPublishedAt());
        dto.setIsActive(annonce.getIsActive());
        dto.setTypeAnnonceId(annonce.getTypeAnnonce() != null ? annonce.getTypeAnnonce().getId() : null);
        dto.setTypeAnnonceNom(annonce.getTypeAnnonce() != null ? annonce.getTypeAnnonce().getNom() : null);
        dto.setCategorieAnnonceId(annonce.getCategorieAnnonce() != null ? annonce.getCategorieAnnonce().getId() : null);
        dto.setCategorieAnnonceNom(annonce.getCategorieAnnonce() != null ? annonce.getCategorieAnnonce().getNom() : null);
        return dto;
    }
}
