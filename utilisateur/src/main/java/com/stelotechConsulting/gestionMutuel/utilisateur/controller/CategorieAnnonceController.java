package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.CategorieAnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.CategorieAnnonceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ICategorieAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories-annonce")
public class CategorieAnnonceController {
    @Autowired
    private ICategorieAnnonceService categorieAnnonceService;

    @PostMapping
    public ResponseEntity<CategorieAnnonceResponseDTO> createCategorieAnnonce(@RequestBody CategorieAnnonceRequestDTO dto) {
        return ResponseEntity.ok(categorieAnnonceService.createCategorieAnnonce(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorieAnnonceResponseDTO> getCategorieAnnonceById(@PathVariable Long id) {
        CategorieAnnonceResponseDTO cat = categorieAnnonceService.getCategorieAnnonceById(id);
        return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CategorieAnnonceResponseDTO>> getAllCategorieAnnonces() {
        return ResponseEntity.ok(categorieAnnonceService.getAllCategorieAnnonces());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorieAnnonceResponseDTO> updateCategorieAnnonce(@PathVariable Long id, @RequestBody CategorieAnnonceRequestDTO dto) {
        CategorieAnnonceResponseDTO cat = categorieAnnonceService.updateCategorieAnnonce(id, dto);
        return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategorieAnnonce(@PathVariable Long id) {
        categorieAnnonceService.deleteCategorieAnnonce(id);
        return ResponseEntity.noContent().build();
    }
}

