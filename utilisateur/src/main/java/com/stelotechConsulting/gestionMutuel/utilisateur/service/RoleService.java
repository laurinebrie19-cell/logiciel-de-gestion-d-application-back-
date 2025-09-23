package com.stelotechConsulting.gestionMutuel.utilisateur.service;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.RoleRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.RoleResponseDTO;


import java.util.List;

public interface RoleService {
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO getRoleById(Long id);
    RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO);
    RoleResponseDTO updateRole(Long id, RoleRequestDTO roleRequestDTO);
    void deleteRole(Long id);
}