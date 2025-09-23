package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CreateSpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.SpecialiteRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.SpecialiteResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ISpecialiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialites")
public class SpecialiteController {

    @Autowired
    private ISpecialiteService specialiteService;

    @PostMapping
    public ResponseEntity<SpecialiteResponseDTO> createSpecialite(@RequestBody CreateSpecialiteRequestDTO dto) {
        return ResponseEntity.ok(specialiteService.createSpecialite(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecialiteResponseDTO> getSpecialiteById(@PathVariable Long id) {
        SpecialiteResponseDTO specialite = specialiteService.getSpecialiteById(id);
        return specialite != null ? ResponseEntity.ok(specialite) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<SpecialiteResponseDTO>> getAllSpecialites() {
        return ResponseEntity.ok(specialiteService.getAllSpecialites());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpecialiteResponseDTO> updateSpecialite(
            @PathVariable Long id,
            @RequestBody SpecialiteRequestDTO dto) {
        SpecialiteResponseDTO updated = specialiteService.updateSpecialite(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialite(@PathVariable Long id) {
        specialiteService.deleteSpecialite(id);
        return ResponseEntity.ok().build();
    }
}
