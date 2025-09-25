package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.AnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.AnnonceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/annonces")
public class AnnonceController {
    @Autowired
    private IAnnonceService annonceService;

    @PostMapping
    public ResponseEntity<AnnonceResponseDTO> createAnnonce(@RequestBody AnnonceRequestDTO dto) {
        return ResponseEntity.ok(annonceService.createAnnonce(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnonceResponseDTO> getAnnonceById(@PathVariable Long id) {
        AnnonceResponseDTO annonce = annonceService.getAnnonceById(id);
        return annonce != null ? ResponseEntity.ok(annonce) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<AnnonceResponseDTO>> getAllAnnonces() {
        return ResponseEntity.ok(annonceService.getAllAnnonces());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnonceResponseDTO> updateAnnonce(@PathVariable Long id, @RequestBody AnnonceRequestDTO dto) {
        AnnonceResponseDTO annonce = annonceService.updateAnnonce(id, dto);
        return annonce != null ? ResponseEntity.ok(annonce) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnnonce(@PathVariable Long id) {
        annonceService.deleteAnnonce(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/category/{categorieAnnonceId}")
    public ResponseEntity<List<AnnonceResponseDTO>> getAnnoncesByCategory(@PathVariable Long categorieAnnonceId) {
        return ResponseEntity.ok(annonceService.getAnnoncesByCategory(categorieAnnonceId));
    }

    @GetMapping("/type/{typeAnnonceId}")
    public ResponseEntity<List<AnnonceResponseDTO>> getAnnoncesByType(@PathVariable Long typeAnnonceId) {
        return ResponseEntity.ok(annonceService.getAnnoncesByType(typeAnnonceId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<AnnonceResponseDTO>> getActiveAnnonces() {
        return ResponseEntity.ok(annonceService.getActiveAnnonces());
    }
}
