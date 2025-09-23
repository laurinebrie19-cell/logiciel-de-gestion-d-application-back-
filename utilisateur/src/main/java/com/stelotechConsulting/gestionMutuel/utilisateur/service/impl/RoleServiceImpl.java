package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.RoleRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.RoleResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Permission;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.PermissionRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.RoleRepository;

import com.stelotechConsulting.gestionMutuel.utilisateur.service.RoleService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

@Override
public List<RoleResponseDTO> getAllRoles() {
    return roleRepository.findAll().stream()
            .filter(role -> !role.getRoleName().equals("SuperAdmin"))
            .map(this::convertToRoleResponseDTO)
            .collect(Collectors.toList());
}

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        return convertToRoleResponseDTO(role);
    }

    @Override
    public RoleResponseDTO createRole(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setRoleDescription(roleRequestDTO.getRoleDescription());
        role.setRoleCreatedAt(roleRequestDTO.getRoleCreatedAt());
        role.setRoleModifiedAt(roleRequestDTO.getRoleModifiedAt());
        role.setRoleCreatedBy(roleRequestDTO.getRoleCreatedBy());
        role.setRoleLastModifiedBy(roleRequestDTO.getRoleLastModifiedBy());

        List<Permission> permissions = permissionRepository.findAllById(roleRequestDTO.getPermissionIds());
        role.setPermissions(permissions);

        Role savedRole = roleRepository.save(role);
        return convertToRoleResponseDTO(savedRole);
    }

    @Override
    public RoleResponseDTO updateRole(Long id, RoleRequestDTO roleRequestDTO) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setRoleName(roleRequestDTO.getRoleName());
        role.setRoleDescription(roleRequestDTO.getRoleDescription());
        role.setRoleCreatedAt(roleRequestDTO.getRoleCreatedAt());
        role.setRoleModifiedAt(roleRequestDTO.getRoleModifiedAt());
        role.setRoleCreatedBy(roleRequestDTO.getRoleCreatedBy());
        role.setRoleLastModifiedBy(roleRequestDTO.getRoleLastModifiedBy());

        List<Permission> permissions = permissionRepository.findAllById(roleRequestDTO.getPermissionIds());
        role.setPermissions(permissions);

        Role updatedRole = roleRepository.save(role);
        return convertToRoleResponseDTO(updatedRole);
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    private RoleResponseDTO convertToRoleResponseDTO(Role role) {
        return modelMapper.map(role, RoleResponseDTO.class);
    }
}