package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EtudiantResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Etudiant;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.EtudiantMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.EtudiantRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IEtudiantService;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.UserRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.RoleRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Niveau;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.NiveauRepository;
import org.mindrot.jbcrypt.BCrypt;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private NiveauRepository niveauRepository;

    @Override
    public EtudiantResponseDTO createEtudiant(EtudiantRequestDTO dto) {
        // Vérifier si un utilisateur existe déjà avec cet email
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà.");
        }
        // Création de l'utilisateur associé
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setFirstName(dto.getPrenom());
        utilisateur.setLastName(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setPassword(BCrypt.hashpw("Student_Infotech", BCrypt.gensalt()));
        utilisateur.setRole("Etudiant");
        utilisateur.setSex(dto.getSexe());
        utilisateur.setDateOfBirth(dto.getDateNaissance());
        utilisateur.setStatus("Actif");
        utilisateur.setValid(true);
        utilisateur.setAddress(""); // Peut être adapté
        utilisateur.setPhoneNumber(""); // Peut être adapté
        // Attribution du rôle
        Role roleEtudiant = roleRepository.findByRoleName("Etudiant").orElseThrow(() -> new RuntimeException("Rôle ETUDIANT introuvable"));
        utilisateur.getRoles().add(roleEtudiant);
        userRepository.save(utilisateur);
        // Création de l'étudiant
        Etudiant etudiant = etudiantMapper.convertToEntity(dto);
        etudiant.setMatricule(generateMatricule(dto));
        etudiant.setUtilisateur(utilisateur);
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
        Niveau niveau = niveauRepository.findById(dto.getNiveauId())
            .orElseThrow(() -> new RuntimeException("Niveau introuvable pour l'id : " + dto.getNiveauId()));
        etudiant.setNiveau(niveau);
        etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.convertToResponseDTO(etudiant);
    }

    @Override
    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    @Override
    public List<EtudiantResponseDTO> getEtudiantsByNiveau(Long niveauId) {
        return etudiantRepository.findByNiveau_Id(niveauId).stream()
            .map(etudiantMapper::convertToResponseDTO)
            .collect(java.util.stream.Collectors.toList());
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