package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SalleRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SalleResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ISalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
public class SalleController {
    @Autowired
    private ISalleService salleService;

    @PostMapping
    public ResponseEntity<SalleResponseDTO> createSalle(@RequestBody SalleRequestDTO dto) {
        return ResponseEntity.ok(salleService.createSalle(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalleResponseDTO> getSalleById(@PathVariable Long id) {
        SalleResponseDTO salle = salleService.getSalleById(id);
        return salle != null ? ResponseEntity.ok(salle) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<SalleResponseDTO>> getAllSalles() {
        return ResponseEntity.ok(salleService.getAllSalles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalleResponseDTO> updateSalle(@PathVariable Long id, @RequestBody SalleRequestDTO dto) {
        SalleResponseDTO salle = salleService.updateSalle(id, dto);
        return salle != null ? ResponseEntity.ok(salle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable Long id) {
        salleService.deleteSalle(id);
        return ResponseEntity.noContent().build();
    }
}

