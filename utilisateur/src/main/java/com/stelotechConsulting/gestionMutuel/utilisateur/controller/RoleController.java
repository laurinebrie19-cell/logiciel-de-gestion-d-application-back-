package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.RoleRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.RoleResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService; // Déclaration du service comme final

    // Injection par constructeur
    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/getAllRoles")
    //@PreAuthorize("hasAuthority('ROLE_READ')")
    public ResponseEntity<List<RoleResponseDTO>> findAll() {
        return new ResponseEntity<>(roleService.getAllRoles(), HttpStatus.OK);
    }
    @GetMapping("/getRoleById/{id}")
    public RoleResponseDTO getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping("/createRole")
    public RoleResponseDTO createRole(@RequestBody RoleRequestDTO roleRequestDTO) {
        return roleService.createRole(roleRequestDTO);
    }

    @PutMapping("/updateRole/{id}")
    public RoleResponseDTO updateRole(@PathVariable Long id, @RequestBody RoleRequestDTO roleRequestDTO) {
        return roleService.updateRole(id, roleRequestDTO);
    }

    @DeleteMapping("/deleteRole/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}
