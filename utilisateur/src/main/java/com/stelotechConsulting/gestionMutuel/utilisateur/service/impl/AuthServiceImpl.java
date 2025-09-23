package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.BadCredentialException;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.ResetPasswordRequestDto;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Permission;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.UserRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.AuthService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

    @Override
    public UserResponseDTO authenticate(String email, String password) throws BadCredentialException {
        // Recherche de l'utilisateur par email
        Utilisateur utilisateur = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialException("Utilisateur non trouvé"));

        logger.info("Mot de passe stocké (haché) : " + utilisateur.getPassword());
        logger.info("Mot de passe fourni : " + password);

        // Comparer le mot de passe fourni avec le mot de passe haché
        if (!passwordEncoder.matches(password, utilisateur.getPassword())) {
            throw new BadCredentialException("Mot de passe incorrect");
        }

        logger.info("Authentification réussie !");

        return convertToUserResponseDTO(utilisateur);
    }


    public void resetPassword(ResetPasswordRequestDto dto) throws BadCredentialException {
        Utilisateur utilisateur = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new BadCredentialException("Utilisateur non trouvé"));

        // Comparer le mot de passe fourni avec le mot de passe haché
        if (passwordEncoder.matches(dto.getOldPassword(), utilisateur.getPassword())) {

            String hashedPassword = BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt());
            utilisateur.setPassword(hashedPassword);
            //utilisateur.setHasAlreadyConnected(true);
            userRepository.save(utilisateur);
        } else {
            throw new BadCredentialException("Mot de passe incorrect");
        }
        logger.info("Modification de mot de passe effectuée avec succès");
    }

    private UserResponseDTO convertToUserResponseDTO(Utilisateur utilisateur) {
        // Extraction des permissions de tous les rôles
        List<String> permissions = utilisateur.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getPermissionName)
                .distinct()
                .collect(Collectors.toList());

        return UserResponseDTO.builder()
                .id(utilisateur.getId())
                .firstName(utilisateur.getFirstName())
                .lastName(utilisateur.getLastName())
                .role(utilisateur.getRole())
                .phoneNumber(utilisateur.getPhoneNumber())
                .email(utilisateur.getEmail())
                .address(utilisateur.getAddress())
                .sex(utilisateur.getSex())
                .dateOfBirth(utilisateur.getDateOfBirth())
                .createdAt(utilisateur.getCreatedAt())
                .modifiedAt(utilisateur.getModifiedAt())
                .createdBy(utilisateur.getCreatedBy())
                .lastModifiedBy(utilisateur.getLastModifiedBy())
                .mustChangePassword(utilisateur.getMustChangePassword())
                .status(utilisateur.getStatus())
                .roles(utilisateur.getRoles().stream()
                        .map(Role::getRoleName)
                        .collect(Collectors.toList()))
                .permissions(permissions)  // Ajout des permissions
                .build();
    }
}
