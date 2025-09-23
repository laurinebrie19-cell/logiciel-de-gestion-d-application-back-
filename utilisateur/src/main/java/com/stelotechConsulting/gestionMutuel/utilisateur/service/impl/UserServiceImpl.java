package com.stelotechConsulting.gestionMutuel.utilisateur.service.impl;

import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.InvalidVerificationCodeException;
import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.UserNotFoundException;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.UserMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.UserRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.FonctionBureau;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Permission;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.FonctionBureauRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.RoleRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.UserRepository;

import com.stelotechConsulting.gestionMutuel.utilisateur.service.EmailService;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FonctionBureauRepository fonctionBureauRepository;



    @Override
    public Utilisateur findEntityById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<Utilisateur> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        Utilisateur utilisateur = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (!utilisateur.isValid()) {
            throw new RuntimeException("User not found");
        }
        return convertToUserResponseDTO(utilisateur);
    }


    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(Utilisateur::isValid)  // Ne récupérer que les utilisateurs valides
                .map(this::convertToUserResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        // Vérifier si l'utilisateur existe déjà
        Optional<Utilisateur> existingUser = userRepository.findByEmail(userRequestDTO.getEmail());

        // Vérifier l'acteur si fonction du bureau est assignée
        if (userRequestDTO.getFonctionBureauId() != null) {
            Utilisateur actor = userRepository.findByEmail(userRequestDTO.getActorEmail())
                    .orElseThrow(() -> new RuntimeException("Acteur non trouvé pour l’habilitation"));

            // Vérifier que l'acteur a la permission ADD_MEMBRE_BUREAU
            List<String> actorPermissions = actor.getRoles().stream()
                    .flatMap(role -> role.getPermissions().stream())
                    .map(Permission::getPermissionName)  // <-- extraire le nom
                    .distinct()
                    .toList();


            if (!actorPermissions.contains("ADD_MEMBRE_BUREAU")) {
                throw new RuntimeException("Acteur non habilité à créer un utilisateur du bureau");
            }
        }

        Utilisateur utilisateur;
        if (existingUser.isPresent()) {
            utilisateur = existingUser.get();

            if (utilisateur.isValid() && "Actif".equals(utilisateur.getStatus())) {
                throw new IllegalArgumentException("L'adresse email est déjà utilisée par un utilisateur actif.");
            }

            // Réactiver l'utilisateur supprimé
            utilisateur.setFirstName(userRequestDTO.getFirstName());
            utilisateur.setLastName(userRequestDTO.getLastName());
            utilisateur.setRole(userRequestDTO.getRole());
            utilisateur.setPhoneNumber(userRequestDTO.getPhoneNumber());
            utilisateur.setMustChangePassword(true);
            utilisateur.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            utilisateur.setAddress(userRequestDTO.getAddress());
            utilisateur.setSex(userRequestDTO.getSex());
            utilisateur.setDateOfBirth(userRequestDTO.getDateOfBirth());
            utilisateur.setStatus("Actif");
            utilisateur.setValid(true);

            // Affecter les rôles si fournis
            if (userRequestDTO.getRoleIds() != null && !userRequestDTO.getRoleIds().isEmpty()) {
                List<Role> roles = roleRepository.findAllById(userRequestDTO.getRoleIds());
                utilisateur.setRoles(roles);
            }

        } else {
            // Création normale d'un nouvel utilisateur
            utilisateur = new Utilisateur();
            utilisateur.setFirstName(userRequestDTO.getFirstName());
            utilisateur.setLastName(userRequestDTO.getLastName());
            utilisateur.setRole(userRequestDTO.getRole());
            utilisateur.setPhoneNumber(userRequestDTO.getPhoneNumber());
            utilisateur.setEmail(userRequestDTO.getEmail());
            utilisateur.setMustChangePassword(true);
            utilisateur.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            utilisateur.setAddress(userRequestDTO.getAddress());
            utilisateur.setSex(userRequestDTO.getSex());
            utilisateur.setDateOfBirth(userRequestDTO.getDateOfBirth());
            utilisateur.setStatus("Actif");
            utilisateur.setValid(true);

            if (userRequestDTO.getRoleIds() != null && !userRequestDTO.getRoleIds().isEmpty()) {
                List<Role> roles = roleRepository.findAllById(userRequestDTO.getRoleIds());
                utilisateur.setRoles(roles);
            }
        }

        // Affecter la fonction du bureau si fournie
        if (userRequestDTO.getFonctionBureauId() != null) {
            FonctionBureau fonctionBureau = fonctionBureauRepository
                    .findById(userRequestDTO.getFonctionBureauId())
                    .orElseThrow(() -> new RuntimeException("FonctionBureau not found"));
            utilisateur.setFonctionBureau(fonctionBureau);
        }


        Utilisateur savedUser = userRepository.save(utilisateur);

        return convertToUserResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        // Trouver l'utilisateur à mettre à jour
        Utilisateur utilisateur = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Mettre à jour uniquement les champs spécifiés dans la requête

        if (userRequestDTO.getFirstName() != null) {
            utilisateur.setFirstName(userRequestDTO.getFirstName());
        }

        if (userRequestDTO.getLastName() != null) {
            utilisateur.setLastName(userRequestDTO.getLastName());
        }

        if (userRequestDTO.getPhoneNumber() != null) {
            utilisateur.setPhoneNumber(userRequestDTO.getPhoneNumber());
        }

        if (userRequestDTO.getEmail() != null) {
            utilisateur.setEmail(userRequestDTO.getEmail());
        }

        if (userRequestDTO.getPassword() != null) {
            utilisateur.setPassword(userRequestDTO.getPassword());
        }

        if (userRequestDTO.getAddress() != null) {
            utilisateur.setAddress(userRequestDTO.getAddress());
        }

        if (userRequestDTO.getSex() != null) {
            utilisateur.setSex(userRequestDTO.getSex());
        }

        if (userRequestDTO.getDateOfBirth() != null) {
            utilisateur.setDateOfBirth(userRequestDTO.getDateOfBirth());
        }


        // Mise à jour des rôles si la liste des IDs de rôles est passée
        if (userRequestDTO.getRoleIds() != null && !userRequestDTO.getRoleIds().isEmpty()) {
            List<Role> roles = roleRepository.findAllById(userRequestDTO.getRoleIds());
            utilisateur.setRoles(roles);
        }

        if (userRequestDTO.getStatus() != null) {
            utilisateur.setStatus(userRequestDTO.getStatus());
        }

        // Mettre à jour la fonction au bureau si fournie
        if (userRequestDTO.getFonctionBureauId() != null) {
            FonctionBureau fonctionBureau = fonctionBureauRepository
                    .findById(userRequestDTO.getFonctionBureauId())
                    .orElseThrow(() -> new RuntimeException("FonctionBureau not found"));
            utilisateur.setFonctionBureau(fonctionBureau);
        }


        // Sauvegarder l'utilisateur mis à jour
        Utilisateur updatedUser = userRepository.save(utilisateur);
        return convertToUserResponseDTO(updatedUser);
    }


    @Override
    public void deleteUser(Long id) {
        Utilisateur utilisateur = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        utilisateur.setValid(false);  // Désactiver l'utilisateur
        userRepository.save(utilisateur);  // Sauvegarder les modifications
    }

    private UserResponseDTO convertToUserResponseDTO(Utilisateur utilisateur) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(utilisateur.getId());
        userResponseDTO.setFirstName(utilisateur.getFirstName());
        userResponseDTO.setLastName(utilisateur.getLastName());
        userResponseDTO.setRole(utilisateur.getRole());
        userResponseDTO.setPhoneNumber(utilisateur.getPhoneNumber());
        userResponseDTO.setEmail(utilisateur.getEmail());
        userResponseDTO.setAddress(utilisateur.getAddress());
        userResponseDTO.setSex(utilisateur.getSex());
        userResponseDTO.setDateOfBirth(utilisateur.getDateOfBirth());
        userResponseDTO.setCreatedAt(utilisateur.getCreatedAt());
        userResponseDTO.setModifiedAt(utilisateur.getModifiedAt());
        userResponseDTO.setCreatedBy(utilisateur.getCreatedBy());
        userResponseDTO.setLastModifiedBy(utilisateur.getLastModifiedBy());
        userResponseDTO.setStatus(utilisateur.getStatus());

        userResponseDTO.setRoles(utilisateur.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList()));

        // Permissions (nom des permissions issues des rôles)
        List<String> permissions = utilisateur.getRoles() == null ? List.of() :
                utilisateur.getRoles().stream()
                        .filter(Objects::nonNull)
                        .flatMap(role -> role.getPermissions().stream().filter(Objects::nonNull))
                        .map(Permission::getPermissionName)
                        .distinct()
                        .collect(Collectors.toList());
        userResponseDTO.setPermissions(permissions);

        // Fonction bureau id (si présente)
        if (utilisateur.getFonctionBureau() != null) {
            userResponseDTO.setFonctionBureauId(utilisateur.getFonctionBureau().getId());
        } else {
            userResponseDTO.setFonctionBureauId(null);
        }
        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getUtilisateursActifs() {
        return userRepository.findAll().stream()
                .filter(user -> user.isValid() && "Actif".equalsIgnoreCase(user.getStatus()))
                .map(this::convertToUserResponseDTO)
                .collect(Collectors.toList());
    }



    private String generateRandomPassword(int length) {
        // Les caractères possibles pour le mot de passe
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        Random random = new Random();
        StringBuilder password = new StringBuilder(length);

        // Générer le mot de passe
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }

    // Méthode pour générer un code de vérification de 5 chiffres
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 10000 + random.nextInt(90000);
        return String.valueOf(code);
    }

    public UserResponseDTO resetPassword(String email, String oldPassword, String newPassword) {
        logger.debug("Resetting password for user: {}", email);

        // Recherche de l'utilisateur par son email
        Optional<Utilisateur> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            // Si l'utilisateur n'est pas trouvé, lancer une exception
            logger.warn("User not found for password reset: {}", email);
            throw new UserNotFoundException("Invalid email");
        }

        Utilisateur user = optionalUser.get();

        // Vérifier si l'ancien mot de passe correspond à celui enregistré
        boolean oldPasswordMatches = passwordEncoder.matches(oldPassword, user.getPassword());
        logger.debug("Old password matches: {}", oldPasswordMatches);

        if (oldPasswordMatches) {
            // Encoder le nouveau mot de passe
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedNewPassword);

            // Désactiver l'obligation de changer le mot de passe
            user.setMustChangePassword(false);

            // Sauvegarder les modifications dans la base de données
            userRepository.save(user);

            logger.debug("Password updated successfully for user: {}", email);

            // Retourner les informations de l'utilisateur sous forme de DTO
            return userMapper.convertToResponseDTO(user);
        } else {
            // Si l'ancien mot de passe est incorrect
            logger.warn("Old password does not match for user: {}", email);
            throw new UserNotFoundException("Invalid old password");
        }
    }

    /**
     * Envoie un code de vérification à l'utilisateur pour réinitialiser son mot de passe.
     */
    public UserResponseDTO resetForgottenPassword(String email) {
        logger.debug("Request to reset password for user with email: {}", email);

        // Étape 1 : Rechercher l'utilisateur par son adresse email
        Optional<Utilisateur> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            // Si l'utilisateur n'existe pas, lancer une exception personnalisée
            logger.warn("User not found for password reset: {}", email);
            throw new UserNotFoundException("Invalid email");
        }

        Utilisateur user = optionalUser.get();

        // Étape 2 : Générer un code de vérification aléatoire (ex : 5 chiffres)
        String verificationCode = generateVerificationCode();

        // Étape 3 : Envoyer le code de vérification à l'adresse email de l'utilisateur
        emailService.sendresetEmail(user.getEmail(), user.getFirstName(), verificationCode);

        // Étape 4 : Enregistrer le code de vérification dans la base de données
        user.setVerificationCode(verificationCode);
        userRepository.save(user); // Sauvegarder l'utilisateur mis à jour

        logger.debug("Verification code sent to user with email: {}", email);

        // Étape 5 : Retourner les informations de l'utilisateur sous forme de DTO
        return userMapper.convertToResponseDTO(user);
    }


    /**
     * Confirme le code de vérification et réinitialise le mot de passe de l'utilisateur.
     *
     * @param email             l'adresse email de l'utilisateur
     * @param verificationCode  le code de vérification reçu par email
     * @param newPassword       le nouveau mot de passe à définir
     * @return                  les informations mises à jour de l'utilisateur sous forme de DTO
     */
    public UserResponseDTO confirmResetPassword(String email, String verificationCode, String newPassword) {
        logger.debug("Confirming password reset for user with email: {}", email);

        // Étape 1 : Rechercher l'utilisateur par son email
        Optional<Utilisateur> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            // Si l'utilisateur n'existe pas, lancer une exception personnalisée
            logger.warn("User not found for password reset: {}", email);
            throw new UserNotFoundException("Invalid email");
        }

        Utilisateur user = optionalUser.get();

        // Étape 2 : Vérifier que le code de vérification fourni correspond à celui enregistré
        if (!verificationCode.equals(user.getVerificationCode())) {
            // Si le code est incorrect, lancer une exception
            logger.warn("Invalid verification code for user with email: {}", email);
            throw new InvalidVerificationCodeException("Invalid verification code");
        }

        // Étape 3 : Encoder le nouveau mot de passe de manière sécurisée
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);

        // Optionnel : indiquer que l'utilisateur n'est pas obligé de changer son mot de passe au prochain login
        user.setMustChangePassword(false);

        // Étape 4 : Nettoyer le code de vérification une fois utilisé
        user.setVerificationCode(null);

        // Étape 5 : Sauvegarder les modifications dans la base de données
        userRepository.save(user);

        logger.debug("Password reset successfully for user with email: {}", email);

        // Étape 6 : Retourner les informations de l'utilisateur mises à jour
        return userMapper.convertToResponseDTO(user);
    }

}