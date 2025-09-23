package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.BadCredentialException;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.LoginRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.ResetPasswordRequestDto;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.impl.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl authService; // Déclare authService comme une variable d'instance

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) throws BadCredentialException {
        try {
            // Utilise authService pour authentifier l'utilisateur
            return ResponseEntity.ok(authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialException e) {
            throw new BadCredentialException(e.getMessage());
        }
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDto dto) throws BadCredentialException {
        try {
            authService.resetPassword(dto);
            return new ResponseEntity<>("Modification réussie", HttpStatus.OK);
        } catch (BadCredentialException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
