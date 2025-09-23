package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.BadCredentialException;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.ResetPasswordRequestDto;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;

public interface AuthService {
    UserResponseDTO authenticate(String email, String password) throws BadCredentialException;
    void resetPassword(ResetPasswordRequestDto dto) throws BadCredentialException;
}
