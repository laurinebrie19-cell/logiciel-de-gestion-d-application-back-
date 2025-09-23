package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.UserRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Permission;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.PermissionService;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    public UserRequestDTO convertToRequestDTO(Utilisateur user) {
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserRequestDTO.class);
    }




    public UserResponseDTO convertToResponseDTO(Utilisateur user) {
        if (user == null) {
            return null;
        }

        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);

        // Mapper explicitement les rôles
        if (user.getRoles() != null) {
            userResponseDTO.setRoles(
                    user.getRoles().stream()
                            .map(role -> role.getRoleName()) // ou role.getNom() selon ta classe Role
                            .toList()
            );
        }

        // mapper explicitement l’ID de la fonction du bureau
        if (user.getFonctionBureau() != null) {
            userResponseDTO.setFonctionBureauId(user.getFonctionBureau().getId());
        }

        return userResponseDTO;
    }












    public Utilisateur convertToEntity(UserRequestDTO userRequestDTO) {
        if (userRequestDTO == null) {
            return null;
        }

        // Mapper les propriétés simples avec ModelMapper
        Utilisateur user = modelMapper.map(userRequestDTO, Utilisateur.class);


        return user;
    }

    public Date convertToDate(LocalDateTime localDateTime) {
        return Timestamp.valueOf(localDateTime);
    }

    private LocalDateTime convertToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public Utilisateur convertToEntity(UserResponseDTO userResponseDTO) {
        if (userResponseDTO == null) {
            return null;
        }
        return modelMapper.map(userResponseDTO, Utilisateur.class);
    }
}
