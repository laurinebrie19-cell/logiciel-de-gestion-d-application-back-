package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FiliereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FiliereResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filieres")
public class FiliereController {
    @Autowired
    private IFiliereService filiereService;

    @PostMapping
    public ResponseEntity<FiliereResponseDTO> createFiliere(@RequestBody FiliereRequestDTO dto) {
        return ResponseEntity.ok(filiereService.createFiliere(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FiliereResponseDTO> getFiliere(@PathVariable Long id) {
        FiliereResponseDTO filiere = filiereService.getFiliereById(id);
        if (filiere == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(filiere);
    }

    @GetMapping
    public ResponseEntity<List<FiliereResponseDTO>> getAllFilieres() {
        return ResponseEntity.ok(filiereService.getAllFilieres());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FiliereResponseDTO> updateFiliere(@PathVariable Long id, @RequestBody FiliereRequestDTO dto) {
        FiliereResponseDTO filiere = filiereService.updateFiliere(id, dto);
        if (filiere == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(filiere);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiliere(@PathVariable Long id) {
        filiereService.deleteFiliere(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/specialites")
    public ResponseEntity<List<com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO>> getSpecialitesByFiliere(@PathVariable Long id) {
        List<com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO> specialites = filiereService.getSpecialitesByFiliereId(id);
        return ResponseEntity.ok(specialites);
    }
}
