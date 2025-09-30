package com.stelotechConsulting.gestionMutuel.utilisateur.service;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.UserRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NiveauResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;


import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO);
    void deleteUser(Long id);
    List<UserResponseDTO> getUtilisateursActifs();

    Utilisateur findEntityById(Long id);

    UserResponseDTO resetPassword(String email, String oldPassword, String newPassword);
    UserResponseDTO resetForgottenPassword(String email);
    UserResponseDTO confirmResetPassword(String email, String verificationCode, String newPassword);
    Optional<Utilisateur> findByEmail(String email);
    List<NiveauResponseDTO> getNiveauxByEnseignant(Long enseignantId);
}