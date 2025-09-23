package com.stelotechConsulting.gestionMutuel.utilisateur.service;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.PermissionRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.PermissionResponseDTO;

import java.util.List;

public interface PermissionService {
    List<PermissionResponseDTO> getAllPermissions();
    PermissionResponseDTO getPermissionById(Long id);
    PermissionResponseDTO createPermission(PermissionRequestDTO permissionRequestDTO);
    PermissionResponseDTO updatePermission(Long id, PermissionRequestDTO permissionRequestDTO);
    void deletePermission(Long id);
}