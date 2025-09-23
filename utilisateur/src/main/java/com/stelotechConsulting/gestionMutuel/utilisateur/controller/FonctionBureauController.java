package com.stelotechConsulting.gestionMutuel.utilisateur.controller;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FonctionBureauRequest;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FonctionBureauResponse;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.impl.FonctionBureauServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fonctions-bureau")
@RequiredArgsConstructor
public class FonctionBureauController {

    private final FonctionBureauServiceImpl service;

    @PostMapping
    public ResponseEntity<FonctionBureauResponse> create(@RequestBody FonctionBureauRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<FonctionBureauResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FonctionBureauResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FonctionBureauResponse> update(
            @PathVariable Long id,
            @RequestBody FonctionBureauRequest request
    ) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}