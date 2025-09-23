package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.FonctionBureauMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FonctionBureauRequest;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FonctionBureauResponse;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.FonctionBureau;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.FonctionBureauRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.IFonctionBureauService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FonctionBureauServiceImpl implements IFonctionBureauService {

    private final FonctionBureauRepository repository;
    private final FonctionBureauMapper mapper;

    @Override
    public FonctionBureauResponse create(FonctionBureauRequest request) {
        FonctionBureau fonction = mapper.toEntity(request);
        repository.findByLibelleIgnoreCase(fonction.getLibelle())
                .ifPresent(f -> {throw new RuntimeException("Cette fonction existe déjà");});
        return mapper.toResponse(repository.save(fonction));
    }

    @Override
    public List<FonctionBureauResponse> getAll() {
        return repository.findAll().stream()
                .filter(FonctionBureau::getValid)
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FonctionBureauResponse getById(Long id) {
        FonctionBureau fonction = repository.findById(id)
                .filter(FonctionBureau::getValid)
                .orElseThrow(() -> new RuntimeException("Fonction introuvable ou désactivée"));
        return mapper.toResponse(fonction);
    }

    @Override
    public FonctionBureauResponse update(Long id, FonctionBureauRequest request) {
        FonctionBureau fonction = repository.findById(id)
                .filter(FonctionBureau::getValid)
                .orElseThrow(() -> new RuntimeException("Fonction introuvable ou désactivée"));
        fonction.setLibelle(request.getLibelle());
        fonction.setDescription(request.getDescription());
        return mapper.toResponse(repository.save(fonction));
    }

    @Override
    public void delete(Long id) {
        FonctionBureau fonction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fonction non trouvée avec id: " + id));
        fonction.setValid(false);
        repository.save(fonction);
    }
}