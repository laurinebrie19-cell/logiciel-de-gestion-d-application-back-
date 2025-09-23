package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CreateSpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Filiere;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Specialite;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.FiliereRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecialiteMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FiliereRepository filiereRepository;

    public SpecialiteResponseDTO convertToResponseDTO(Specialite specialite) {
        if (specialite == null) return null;
        SpecialiteResponseDTO dto = modelMapper.map(specialite, SpecialiteResponseDTO.class);
        if (specialite.getFiliere() != null) {
            dto.setFiliereId(specialite.getFiliere().getId());
            dto.setFiliereNom(specialite.getFiliere().getNom());
        }
        return dto;
    }

    public Specialite convertToEntity(SpecialiteRequestDTO dto) {
        if (dto == null) return null;
        Specialite specialite = modelMapper.map(dto, Specialite.class);
        if (dto.getFiliereId() != null) {
            Filiere filiere = filiereRepository.findById(dto.getFiliereId()).orElse(null);
            specialite.setFiliere(filiere);
        }
        return specialite;
    }

    public Specialite convertCreateDtoToEntity(CreateSpecialiteRequestDTO dto) {
        if (dto == null) return null;

        Specialite specialite = new Specialite();
        specialite.setNom(dto.getNom());

        if (dto.getFiliereId() != null) {
            Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
            specialite.setFiliere(filiere);
        }

        return specialite;
    }

    public Specialite updateEntityFromDTO(Specialite specialite, SpecialiteRequestDTO dto) {
        if (dto == null) return specialite;

        if (dto.getNom() != null) {
            specialite.setNom(dto.getNom());
        }

        if (dto.getFiliereId() != null) {
            Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière non trouvée"));
            specialite.setFiliere(filiere);
        }

        return specialite;
    }
}
