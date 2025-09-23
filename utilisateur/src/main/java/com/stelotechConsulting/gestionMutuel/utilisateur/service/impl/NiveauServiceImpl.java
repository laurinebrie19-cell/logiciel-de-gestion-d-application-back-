 package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NiveauRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NiveauResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Niveau;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.NiveauMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.NiveauRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.INiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NiveauServiceImpl implements INiveauService {
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private NiveauMapper niveauMapper;

    @Override
    public NiveauResponseDTO createNiveau(NiveauRequestDTO dto) {
        Niveau niveau = niveauMapper.convertToEntity(dto);
        niveau = niveauRepository.save(niveau);
        return niveauMapper.convertToResponseDTO(niveau);
    }

    @Override
    public NiveauResponseDTO getNiveauById(Long id) {
        Niveau niveau = niveauRepository.findById(id).orElse(null);
        return niveauMapper.convertToResponseDTO(niveau);
    }

    @Override
    public List<NiveauResponseDTO> getAllNiveaux() {
        return niveauRepository.findAll().stream()
                .map(niveauMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NiveauResponseDTO updateNiveau(Long id, NiveauRequestDTO dto) {
        Niveau niveau = niveauRepository.findById(id).orElse(null);
        if (niveau == null) return null;
        niveau.setNom(dto.getNom());
        niveau = niveauRepository.save(niveau);
        return niveauMapper.convertToResponseDTO(niveau);
    }

    @Override
    public void deleteNiveau(Long id) {
        niveauRepository.deleteById(id);
    }
}

