package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EtudiantResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Etudiant;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.EtudiantMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.EtudiantRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class EtudiantServiceImpl implements IEtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private EtudiantMapper etudiantMapper;

    @Override
    public EtudiantResponseDTO createEtudiant(EtudiantRequestDTO dto) {
        Etudiant etudiant = etudiantMapper.convertToEntity(dto);
        etudiant.setMatricule(generateMatricule(dto));
        etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.convertToResponseDTO(etudiant);
    }

    @Override
    public EtudiantResponseDTO getEtudiantById(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        return etudiantMapper.convertToResponseDTO(etudiant);
    }

    @Override
    public List<EtudiantResponseDTO> getAllEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(etudiantMapper::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EtudiantResponseDTO updateEtudiant(Long id, EtudiantRequestDTO dto) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant == null) return null;
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setSexe(dto.getSexe());
        etudiant.setNiveau(dto.getNiveau());
        etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.convertToResponseDTO(etudiant);
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    private String generateMatricule(EtudiantRequestDTO dto) {
        String nom = dto.getNom();
        String prefix = nom.length() >= 2 ? nom.substring(0, 2).toUpperCase() : nom.toUpperCase();
        int year = LocalDate.now().getYear() % 100;
        char randomChar = (char) ('A' + new Random().nextInt(26));
        int randomNumber = 1000 + new Random().nextInt(9000);
        String matricule = String.format("%02d%s%c%d", year, prefix, randomChar, randomNumber);
        // Vérifier unicité
        while (etudiantRepository.existsByMatricule(matricule)) {
            randomChar = (char) ('A' + new Random().nextInt(26));
            randomNumber = 1000 + new Random().nextInt(9000);
            matricule = String.format("%02d%s%c%d", year, prefix, randomChar, randomNumber);
        }
        return matricule;
    }
}

