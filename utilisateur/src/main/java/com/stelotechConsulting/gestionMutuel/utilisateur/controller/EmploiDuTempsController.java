package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.EmploiDuTempsRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.EmploiDuTempsResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IEmploiDuTempsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emplois-du-temps")
public class EmploiDuTempsController {
    @Autowired
    private IEmploiDuTempsService emploiDuTempsService;

    @PostMapping
    public ResponseEntity<EmploiDuTempsResponseDTO> createEmploiDuTemps(@RequestBody EmploiDuTempsRequestDTO dto) {
        return ResponseEntity.ok(emploiDuTempsService.createEmploiDuTemps(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploiDuTempsResponseDTO> getEmploiDuTempsById(@PathVariable Long id) {
        EmploiDuTempsResponseDTO emploi = emploiDuTempsService.getEmploiDuTempsById(id);
        return emploi != null ? ResponseEntity.ok(emploi) : ResponseEntity.notFound().build();
    }

    @GetMapping("/niveau/{niveauId}")
    public ResponseEntity<List<EmploiDuTempsResponseDTO>> getEmploisDuTempsByNiveau(@PathVariable Long niveauId) {
        return ResponseEntity.ok(emploiDuTempsService.getEmploisDuTempsByNiveau(niveauId));
    }

    @GetMapping
    public ResponseEntity<List<EmploiDuTempsResponseDTO>> getAllEmploisDuTemps() {
        return ResponseEntity.ok(emploiDuTempsService.getAllEmploisDuTemps());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmploiDuTempsResponseDTO> updateEmploiDuTemps(@PathVariable Long id, @RequestBody EmploiDuTempsRequestDTO dto) {
        EmploiDuTempsResponseDTO emploi = emploiDuTempsService.updateEmploiDuTemps(id, dto);
        return emploi != null ? ResponseEntity.ok(emploi) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploiDuTemps(@PathVariable Long id) {
        emploiDuTempsService.deleteEmploiDuTemps(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<EmploiDuTempsResponseDTO>> getEmploisDuTempsByFiliere(@PathVariable Long filiereId) {
        return ResponseEntity.ok(emploiDuTempsService.getEmploisDuTempsByFiliere(filiereId));
    }

    @GetMapping("/filiere/{filiereId}/niveau/{niveauId}")
    public ResponseEntity<List<EmploiDuTempsResponseDTO>> getEmploisDuTempsByFiliereAndNiveau(@PathVariable Long filiereId, @PathVariable Long niveauId) {
        return ResponseEntity.ok(emploiDuTempsService.getEmploisDuTempsByFiliereAndNiveau(filiereId, niveauId));
    }
}
