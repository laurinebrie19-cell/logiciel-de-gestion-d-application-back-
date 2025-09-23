package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.PermissionRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.PermissionResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Permission;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.PermissionRepository;

import com.stelotechConsulting.gestionMutuel.utilisateur.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionRepository.findAll().stream()
                .map(this::convertToPermissionResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponseDTO getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        return convertToPermissionResponseDTO(permission);
    }

    @Override
    public PermissionResponseDTO createPermission(PermissionRequestDTO permissionRequestDTO) {
        Permission permission = new Permission();
        permission.setPermissionName(permissionRequestDTO.getPermissionName());
        permission.setPermissionDescription(permissionRequestDTO.getPermissionDescription());
        permission.setPermissionCreatedAt(permissionRequestDTO.getPermissionCreatedAt());
        permission.setPermissionModifiedAt(permissionRequestDTO.getPermissionModifiedAt());
        permission.setPermissionCreatedBy(permissionRequestDTO.getPermissionCreatedBy());
        permission.setPermissionLastModifiedBy(permissionRequestDTO.getPermissionLastModifiedBy());

        Permission savedPermission = permissionRepository.save(permission);
        return convertToPermissionResponseDTO(savedPermission);
    }

    @Override
    public PermissionResponseDTO updatePermission(Long id, PermissionRequestDTO permissionRequestDTO) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        permission.setPermissionName(permissionRequestDTO.getPermissionName());
        permission.setPermissionDescription(permissionRequestDTO.getPermissionDescription());
        permission.setPermissionCreatedAt(permissionRequestDTO.getPermissionCreatedAt());
        permission.setPermissionModifiedAt(permissionRequestDTO.getPermissionModifiedAt());
        permission.setPermissionCreatedBy(permissionRequestDTO.getPermissionCreatedBy());
        permission.setPermissionLastModifiedBy(permissionRequestDTO.getPermissionLastModifiedBy());

        Permission updatedPermission = permissionRepository.save(permission);
        return convertToPermissionResponseDTO(updatedPermission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    private PermissionResponseDTO convertToPermissionResponseDTO(Permission permission) {
        PermissionResponseDTO permissionResponseDTO = new PermissionResponseDTO();
        permissionResponseDTO.setId(permission.getId());
        permissionResponseDTO.setPermissionName(permission.getPermissionName());
        permissionResponseDTO.setPermissionDescription(permission.getPermissionDescription());
        permissionResponseDTO.setPermissionCreatedAt(permission.getPermissionCreatedAt());
        permissionResponseDTO.setPermissionModifiedAt(permission.getPermissionModifiedAt());
        permissionResponseDTO.setPermissionCreatedBy(permission.getPermissionCreatedBy());
        permissionResponseDTO.setPermissionLastModifiedBy(permission.getPermissionLastModifiedBy());
        return permissionResponseDTO;
    }
}