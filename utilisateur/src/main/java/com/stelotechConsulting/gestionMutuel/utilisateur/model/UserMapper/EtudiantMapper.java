package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EtudiantResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Etudiant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {
    @Autowired
    private ModelMapper modelMapper;

    public EtudiantResponseDTO convertToResponseDTO(Etudiant etudiant) {
        if (etudiant == null) return null;
        
        EtudiantResponseDTO dto = new EtudiantResponseDTO();
        dto.setId(etudiant.getId());
        dto.setNom(etudiant.getNom());
        dto.setPrenom(etudiant.getPrenom());
        dto.setDateNaissance(etudiant.getDateNaissance());
        dto.setMatricule(etudiant.getMatricule());
        dto.setSexe(etudiant.getSexe());
        dto.setEmail(etudiant.getEmail());
        
        if (etudiant.getNiveau() != null) {
            dto.setNiveau(etudiant.getNiveau().getNom());
        }
        
        return dto;
    }

    public Etudiant convertToEntity(EtudiantRequestDTO dto) {
        if (dto == null) return null;
        
        Etudiant etudiant = new Etudiant();
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setDateNaissance(dto.getDateNaissance());
        etudiant.setSexe(dto.getSexe());
        etudiant.setEmail(dto.getEmail());
        
        return etudiant;
    }
}