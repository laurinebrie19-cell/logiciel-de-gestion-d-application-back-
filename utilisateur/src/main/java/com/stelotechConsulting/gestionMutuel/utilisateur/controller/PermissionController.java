package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.PermissionRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.PermissionResponseDTO;

import com.stelotechConsulting.gestionMutuel.utilisateur.service.PermissionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService; // Déclaration du service comme final

    // Injection par constructeur
    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/getAllPermissions")
    public List<PermissionResponseDTO> getAllPermissions() {
        return permissionService.getAllPermissions();
    }

    @GetMapping("/getPermissionById/{id}")
    public PermissionResponseDTO getPermissionById(@PathVariable Long id) {
        return permissionService.getPermissionById(id);
    }

    @PostMapping("/createPermission")
    public PermissionResponseDTO createPermission(@RequestBody PermissionRequestDTO permissionRequestDTO) {
        return permissionService.createPermission(permissionRequestDTO);
    }

    @PutMapping("/updatePermission/{id}")
    public PermissionResponseDTO updatePermission(@PathVariable Long id, @RequestBody PermissionRequestDTO permissionRequestDTO) {
        return permissionService.updatePermission(id, permissionRequestDTO);
    }

    @DeleteMapping("/deletePermission/{id}")
    public void deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
    }
}
