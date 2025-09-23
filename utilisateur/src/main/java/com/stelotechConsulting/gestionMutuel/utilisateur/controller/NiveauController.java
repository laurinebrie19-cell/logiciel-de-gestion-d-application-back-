package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.NiveauRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NiveauResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.INiveauService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/niveaux")
public class NiveauController {
    @Autowired
    private INiveauService niveauService;

    @PostMapping
    public ResponseEntity<NiveauResponseDTO> createNiveau(@RequestBody NiveauRequestDTO dto) {
        return ResponseEntity.ok(niveauService.createNiveau(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NiveauResponseDTO> getNiveau(@PathVariable Long id) {
        NiveauResponseDTO niveau = niveauService.getNiveauById(id);
        if (niveau == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(niveau);
    }

    @GetMapping
    public ResponseEntity<List<NiveauResponseDTO>> getAllNiveaux() {
        return ResponseEntity.ok(niveauService.getAllNiveaux());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NiveauResponseDTO> updateNiveau(@PathVariable Long id, @RequestBody NiveauRequestDTO dto) {
        NiveauResponseDTO niveau = niveauService.updateNiveau(id, dto);
        if (niveau == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(niveau);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNiveau(@PathVariable Long id) {
        niveauService.deleteNiveau(id);
        return ResponseEntity.noContent().build();
    }
}
