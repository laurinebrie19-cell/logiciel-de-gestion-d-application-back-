package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FiliereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FiliereResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Filiere;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.FiliereMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.FiliereRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IFiliereService;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.SpecialiteMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.SpecialiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FiliereServiceImpl implements IFiliereService {
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private FiliereMapper filiereMapper;
    @Autowired
    private SpecialiteRepository specialiteRepository;
    @Autowired
    private SpecialiteMapper specialiteMapper;

    @Override
    public FiliereResponseDTO createFiliere(FiliereRequestDTO dto) {
        Filiere filiere = filiereMapper.convertToEntity(dto);
        filiere = filiereRepository.save(filiere);
        return filiereMapper.convertToResponseDTO(filiere);
    }

    @Override
    public FiliereResponseDTO getFiliereById(Long id) {
        Filiere filiere = filiereRepository.findById(id).orElse(null);
        return filiereMapper.convertToResponseDTO(filiere);
    }

    @Override
    public List<FiliereResponseDTO> getAllFilieres() {
        return filiereRepository.findAll().stream()
                .map(filiereMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public FiliereResponseDTO updateFiliere(Long id, FiliereRequestDTO dto) {
        Filiere filiere = filiereRepository.findById(id).orElse(null);
        if (filiere == null) return null;
        filiere.setNom(dto.getNom());
        filiere = filiereRepository.save(filiere);
        return filiereMapper.convertToResponseDTO(filiere);
    }

    @Override
    public void deleteFiliere(Long id) {
        filiereRepository.deleteById(id);
    }

    @Override
    public java.util.List<SpecialiteResponseDTO> getSpecialitesByFiliereId(Long filiereId) {
        return specialiteRepository.findByFiliere_Id(filiereId)
                .stream()
                .map(specialiteMapper::convertToResponseDTO)
                .collect(java.util.stream.Collectors.toList());
    }
}
