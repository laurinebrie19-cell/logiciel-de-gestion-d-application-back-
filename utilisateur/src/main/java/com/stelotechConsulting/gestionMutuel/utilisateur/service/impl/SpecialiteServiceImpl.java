package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CreateSpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Specialite;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.SpecialiteMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.SpecialiteRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ISpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SpecialiteServiceImpl implements ISpecialiteService {
    @Autowired
    private SpecialiteRepository specialiteRepository;
    @Autowired
    private SpecialiteMapper specialiteMapper;

    @Override
    public SpecialiteResponseDTO createSpecialite(CreateSpecialiteRequestDTO dto) {
        // Vérifier si une spécialité avec le même nom existe déjà dans la même filière
       /* if (specialiteRepository.existsByNomAndFiliere_Id(dto.getNom(), dto.getFiliereId())) {
            throw new RuntimeException("Une spécialité avec ce nom existe déjà dans cette filière");
        }*/

        // Créer une nouvelle spécialité sans ID
        Specialite specialite = specialiteMapper.convertCreateDtoToEntity(dto);
        specialite = specialiteRepository.save(specialite);
        return specialiteMapper.convertToResponseDTO(specialite);
    }

    @Override
    public SpecialiteResponseDTO getSpecialiteById(Long id) {
        Specialite specialite = specialiteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Spécialité non trouvée"));
        return specialiteMapper.convertToResponseDTO(specialite);
    }

    @Override
    public List<SpecialiteResponseDTO> getAllSpecialites() {
        return specialiteRepository.findAll().stream()
                .map(specialiteMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialiteResponseDTO updateSpecialite(Long id, SpecialiteRequestDTO dto) {
        Specialite specialite = specialiteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Spécialité non trouvée"));

        // Vérifier si le nouveau nom n'existe pas déjà dans la même filière (sauf pour la spécialité courante)
     /*   if (dto.getNom() != null && !dto.getNom().equals(specialite.getNom()) &&
            specialiteRepository.existsByNomAndFiliere_Id(dto.getNom(), specialite.getFiliere().getId())) {
            throw new RuntimeException("Une spécialité avec ce nom existe déjà dans cette filière");
        }*/

        specialite = specialiteMapper.updateEntityFromDTO(specialite, dto);
        specialite = specialiteRepository.save(specialite);
        return specialiteMapper.convertToResponseDTO(specialite);
    }

    @Override
    public void deleteSpecialite(Long id) {
        specialiteRepository.deleteById(id);
    }
}