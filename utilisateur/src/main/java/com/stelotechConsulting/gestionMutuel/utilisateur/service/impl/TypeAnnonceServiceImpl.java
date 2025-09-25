package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.TypeAnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.TypeAnnonceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.TypeAnnonce;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.TypeAnnonceRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ITypeAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeAnnonceServiceImpl implements ITypeAnnonceService {
    @Autowired
    private TypeAnnonceRepository typeAnnonceRepository;

    @Override
    public TypeAnnonceResponseDTO createTypeAnnonce(TypeAnnonceRequestDTO dto) {
        TypeAnnonce type = TypeAnnonce.builder()
            .nom(dto.getNom())
            .description(dto.getDescription())
            .build();
        type = typeAnnonceRepository.save(type);
        return toResponseDTO(type);
    }

    @Override
    public TypeAnnonceResponseDTO getTypeAnnonceById(Long id) {
        TypeAnnonce type = typeAnnonceRepository.findById(id).orElse(null);
        return type != null ? toResponseDTO(type) : null;
    }

    @Override
    public List<TypeAnnonceResponseDTO> getAllTypeAnnonces() {
        return typeAnnonceRepository.findAll().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public TypeAnnonceResponseDTO updateTypeAnnonce(Long id, TypeAnnonceRequestDTO dto) {
        TypeAnnonce type = typeAnnonceRepository.findById(id).orElse(null);
        if (type == null) return null;
        type.setNom(dto.getNom());
        type.setDescription(dto.getDescription());
        type = typeAnnonceRepository.save(type);
        return toResponseDTO(type);
    }

    @Override
    public void deleteTypeAnnonce(Long id) {
        typeAnnonceRepository.deleteById(id);
    }

    private TypeAnnonceResponseDTO toResponseDTO(TypeAnnonce type) {
        TypeAnnonceResponseDTO dto = new TypeAnnonceResponseDTO();
        dto.setId(type.getId());
        dto.setNom(type.getNom());
        dto.setDescription(type.getDescription());
        return dto;
    }
}

