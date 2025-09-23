package com.stelotechConsulting.gestionMutuel.utilisateur.service;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FonctionBureauRequest;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FonctionBureauResponse;

import java.util.List;

public interface IFonctionBureauService {

    FonctionBureauResponse create(FonctionBureauRequest request);

    List<FonctionBureauResponse> getAll();

    FonctionBureauResponse getById(Long id);

    FonctionBureauResponse update(Long id, FonctionBureauRequest request);

    void delete(Long id);
}