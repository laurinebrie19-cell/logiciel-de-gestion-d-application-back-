package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.TypeAnnonceRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.TypeAnnonceResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.ITypeAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types-annonce")
public class TypeAnnonceController {
    @Autowired
    private ITypeAnnonceService typeAnnonceService;

    @PostMapping
    public ResponseEntity<TypeAnnonceResponseDTO> createTypeAnnonce(@RequestBody TypeAnnonceRequestDTO dto) {
        return ResponseEntity.ok(typeAnnonceService.createTypeAnnonce(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeAnnonceResponseDTO> getTypeAnnonceById(@PathVariable Long id) {
        TypeAnnonceResponseDTO type = typeAnnonceService.getTypeAnnonceById(id);
        return type != null ? ResponseEntity.ok(type) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<TypeAnnonceResponseDTO>> getAllTypeAnnonces() {
        return ResponseEntity.ok(typeAnnonceService.getAllTypeAnnonces());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeAnnonceResponseDTO> updateTypeAnnonce(@PathVariable Long id, @RequestBody TypeAnnonceRequestDTO dto) {
        TypeAnnonceResponseDTO type = typeAnnonceService.updateTypeAnnonce(id, dto);
        return type != null ? ResponseEntity.ok(type) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeAnnonce(@PathVariable Long id) {
        typeAnnonceService.deleteTypeAnnonce(id);
        return ResponseEntity.noContent().build();
    }
}

