package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SalleRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SalleResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Salle;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.SalleRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ISalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SalleServiceImpl implements ISalleService {
    @Autowired
    private SalleRepository salleRepository;

    @Override
    public SalleResponseDTO createSalle(SalleRequestDTO dto) {
        if (salleRepository.existsByNom(dto.getNom())) {
            throw new IllegalArgumentException("Une salle avec ce nom existe déjà.");
        }
        Salle salle = Salle.builder()
                .nom(dto.getNom())
                .capacite(dto.getCapacite())
                .build();
        salle = salleRepository.save(salle);
        return toResponseDTO(salle);
    }

    @Override
    public SalleResponseDTO getSalleById(Long id) {
        Salle salle = salleRepository.findById(id).orElse(null);
        return salle != null ? toResponseDTO(salle) : null;
    }

    @Override
    public List<SalleResponseDTO> getAllSalles() {
        return salleRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SalleResponseDTO updateSalle(Long id, SalleRequestDTO dto) {
        Salle salle = salleRepository.findById(id).orElse(null);
        if (salle == null) return null;
        salle.setNom(dto.getNom());
        salle.setCapacite(dto.getCapacite());
        salle = salleRepository.save(salle);
        return toResponseDTO(salle);
    }

    @Override
    public void deleteSalle(Long id) {
        salleRepository.deleteById(id);
    }

    private SalleResponseDTO toResponseDTO(Salle salle) {
        SalleResponseDTO dto = new SalleResponseDTO();
        dto.setId(salle.getId());
        dto.setNom(salle.getNom());
        dto.setCapacite(salle.getCapacite());
        return dto;
    }
}

