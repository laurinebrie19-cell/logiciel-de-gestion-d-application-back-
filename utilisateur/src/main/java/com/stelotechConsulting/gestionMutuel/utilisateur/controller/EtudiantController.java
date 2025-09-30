package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EtudiantRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EtudiantResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private IEtudiantService etudiantService;

    @PostMapping
    public ResponseEntity<EtudiantResponseDTO> createEtudiant(@RequestBody EtudiantRequestDTO dto) {
        return ResponseEntity.ok(etudiantService.createEtudiant(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EtudiantResponseDTO> getEtudiant(@PathVariable Long id) {
        EtudiantResponseDTO etudiant = etudiantService.getEtudiantById(id);
        if (etudiant == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(etudiant);
    }

    @GetMapping
    public ResponseEntity<List<EtudiantResponseDTO>> getAllEtudiants() {
        return ResponseEntity.ok(etudiantService.getAllEtudiants());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EtudiantResponseDTO> updateEtudiant(@PathVariable Long id, @RequestBody EtudiantRequestDTO dto) {
        EtudiantResponseDTO etudiant = etudiantService.updateEtudiant(id, dto);
        if (etudiant == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(etudiant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        etudiantService.deleteEtudiant(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/niveau/{niveauId}")
    public ResponseEntity<List<EtudiantResponseDTO>> getEtudiantsByNiveau(@PathVariable Long niveauId) {
        return ResponseEntity.ok(etudiantService.getEtudiantsByNiveau(niveauId));
    }
}
