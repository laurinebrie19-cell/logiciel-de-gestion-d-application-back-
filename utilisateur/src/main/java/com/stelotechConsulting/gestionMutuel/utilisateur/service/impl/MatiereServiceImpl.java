package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.MatiereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.MatiereResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Matiere;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.MatiereMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.MatiereRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.NiveauRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.SpecialiteRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MatiereServiceImpl implements IMatiereService {
    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private MatiereMapper matiereMapper;
    @Autowired
    private NiveauRepository niveauRepository;
    @Autowired
    private SpecialiteRepository specialiteRepository;

    @Override
    public MatiereResponseDTO createMatiere(MatiereRequestDTO dto) {
        var niveau = niveauRepository.findById(dto.getNiveauId())
            .orElseThrow(() -> new IllegalArgumentException("Niveau avec l'ID " + dto.getNiveauId() + " non trouvé"));
        var specialite = specialiteRepository.findById(dto.getSpecialiteId())
            .orElseThrow(() -> new IllegalArgumentException("Spécialité avec l'ID " + dto.getSpecialiteId() + " non trouvée"));

        Matiere matiere = matiereMapper.convertToEntity(dto);
        matiere.setNiveau(niveau);
        matiere.setSpecialite(specialite);
        matiere = matiereRepository.save(matiere);
        return matiereMapper.convertToResponseDTO(matiere);
    }

    @Override
    public MatiereResponseDTO getMatiereById(Long id) {
        Matiere matiere = matiereRepository.findById(id).orElse(null);
        return matiereMapper.convertToResponseDTO(matiere);
    }

    @Override
    public List<MatiereResponseDTO> getAllMatieres() {
        return matiereRepository.findAll().stream()
                .map(matiereMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MatiereResponseDTO updateMatiere(Long id, MatiereRequestDTO dto) {
        Matiere matiere = matiereRepository.findById(id).orElse(null);
        if (matiere == null) return null;
        matiere.setNom(dto.getNom());
        matiere.setCredit(dto.getCredit());
        matiere.setDescription(dto.getDescription());
        matiere.setNiveau(niveauRepository.findById(dto.getNiveauId()).orElse(null));
        matiere.setSpecialite(specialiteRepository.findById(dto.getSpecialiteId()).orElse(null));
        matiere = matiereRepository.save(matiere);
        return matiereMapper.convertToResponseDTO(matiere);
    }

    @Override
    public void deleteMatiere(Long id) {
        matiereRepository.deleteById(id);
    }

    @Override
    public List<MatiereResponseDTO> getMatieresByNiveau(Long niveauId) {
        return matiereRepository.findByNiveauId(niveauId).stream()
                .map(matiereMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatiereResponseDTO> getMatieresBySpecialite(Long specialiteId) {
        return matiereRepository.findBySpecialiteId(specialiteId).stream()
                .map(matiereMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatiereResponseDTO> getMatieresByNiveauAndSpecialite(Long niveauId, Long specialiteId) {
        return matiereRepository.findByNiveauIdAndSpecialiteId(niveauId, specialiteId).stream()
                .map(matiereMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MatiereResponseDTO> getMatieresTroncCommunByNiveau(Long niveauId) {
        return matiereRepository.findByNiveauIdAndTroncCommunTrue(niveauId)
            .stream()
            .map(matiereMapper::convertToResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<MatiereResponseDTO> getMatieresSpecialiteByNiveau(Long niveauId, Long specialiteId) {
        return matiereRepository.findByNiveauIdAndSpecialiteIdAndTroncCommunFalse(niveauId, specialiteId)
            .stream()
            .map(matiereMapper::convertToResponseDTO)
            .collect(Collectors.toList());
    }
}
