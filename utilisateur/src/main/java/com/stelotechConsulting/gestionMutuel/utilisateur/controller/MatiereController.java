package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.MatiereRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.MatiereResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {
    @Autowired
    private IMatiereService matiereService;

    @PostMapping
    public ResponseEntity<MatiereResponseDTO> createMatiere(@RequestBody MatiereRequestDTO dto) {
        return ResponseEntity.ok(matiereService.createMatiere(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatiereResponseDTO> getMatiere(@PathVariable Long id) {
        MatiereResponseDTO matiere = matiereService.getMatiereById(id);
        if (matiere == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(matiere);
    }

    @GetMapping
    public ResponseEntity<List<MatiereResponseDTO>> getAllMatieres() {
        return ResponseEntity.ok(matiereService.getAllMatieres());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatiereResponseDTO> updateMatiere(@PathVariable Long id, @RequestBody MatiereRequestDTO dto) {
        MatiereResponseDTO matiere = matiereService.updateMatiere(id, dto);
        if (matiere == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(matiere);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        matiereService.deleteMatiere(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/niveau/{niveauId}")
    public ResponseEntity<List<MatiereResponseDTO>> getMatieresByNiveau(@PathVariable Long niveauId) {
        return ResponseEntity.ok(matiereService.getMatieresByNiveau(niveauId));
    }

    @GetMapping("/specialite/{specialiteId}")
    public ResponseEntity<List<MatiereResponseDTO>> getMatieresBySpecialite(@PathVariable Long specialiteId) {
        return ResponseEntity.ok(matiereService.getMatieresBySpecialite(specialiteId));
    }

    @GetMapping("/niveau/{niveauId}/specialite/{specialiteId}")
    public ResponseEntity<List<MatiereResponseDTO>> getMatieresByNiveauAndSpecialite(@PathVariable Long niveauId, @PathVariable Long specialiteId) {
        return ResponseEntity.ok(matiereService.getMatieresByNiveauAndSpecialite(niveauId, specialiteId));
    }

    @GetMapping("/niveau/{niveauId}/tronc-commun")
    public ResponseEntity<List<MatiereResponseDTO>> getMatieresTroncCommunByNiveau(@PathVariable Long niveauId) {
        return ResponseEntity.ok(matiereService.getMatieresTroncCommunByNiveau(niveauId));
    }

    @GetMapping("/niveau/{niveauId}/specialite/{specialiteId}/specialite-seule")
    public ResponseEntity<List<MatiereResponseDTO>> getMatieresSpecialiteByNiveau(@PathVariable Long niveauId, @PathVariable Long specialiteId) {
        return ResponseEntity.ok(matiereService.getMatieresSpecialiteByNiveau(niveauId, specialiteId));
    }
}
