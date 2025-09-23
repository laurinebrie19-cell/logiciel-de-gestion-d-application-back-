package com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper;


import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.FonctionBureauRequest;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.FonctionBureauResponse;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.FonctionBureau;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FonctionBureauMapper {

    private final ModelMapper modelMapper;

    public FonctionBureauMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public FonctionBureau toEntity(FonctionBureauRequest request) {
        return modelMapper.map(request, FonctionBureau.class);
    }

    public FonctionBureauResponse toResponse(FonctionBureau entity) {
        return modelMapper.map(entity, FonctionBureauResponse.class);
    }
}
