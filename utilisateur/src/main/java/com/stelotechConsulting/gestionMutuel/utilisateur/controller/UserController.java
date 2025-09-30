package com.stelotechConsulting.gestionMutuel.utilisateur.controller;

import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.InvalidVerificationCodeException;
import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.NotFoundException;
import com.stelotechConsulting.gestionMutuel.utilisateur.exceptions.UserNotFoundException;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.UserMapper.UserMapper;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.ForcerPasswordRequest;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.ResetPasswordRequest;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos.UserRequestDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.UserResponseDTO;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos.NiveauResponseDTO;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.service.UserService;
import org.mindrot.jbcrypt.BCrypt; // Utiliser BCrypt directement
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;  // Déclarer le mapper

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;    // Injection dans le constructeur
    }

    @GetMapping("/email/{email}")
    public UserResponseDTO getUserByEmail(@PathVariable String email) {
        Utilisateur user = userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouvé avec email: " + email));
        return userMapper.convertToResponseDTO(user);
    }

    @GetMapping("/getAllUsers")
    public List<UserResponseDTO> getAllUsersWithoutSuperAdmin() {
        return userService.getAllUsers()
                .stream()
                .filter(user -> user.getRoles().stream()
                        .noneMatch(roleName -> "SuperAdmin".equals(roleName))) // Exclure les utilisateurs avec le rôle "SuperAdmin"
                .map(user -> {
                    // Mapper chaque utilisateur vers un DTO avec les rôles sous forme de tableau
                    UserResponseDTO userResponseDTO = new UserResponseDTO();
                    userResponseDTO.setId(user.getId());
                    userResponseDTO.setFirstName(user.getFirstName());
                    userResponseDTO.setLastName(user.getLastName());
                    userResponseDTO.setRole(user.getRole());
                    userResponseDTO.setPhoneNumber(user.getPhoneNumber());
                    userResponseDTO.setEmail(user.getEmail());
                    userResponseDTO.setStatus(user.getStatus());

//                    userResponseDTO.setPassword(user.getPassword()); // Ajout du champ password
                    userResponseDTO.setAddress(user.getAddress()); // Ajout du champ address
                    userResponseDTO.setSex(user.getSex()); // Ajout du champ sex
                    userResponseDTO.setDateOfBirth(user.getDateOfBirth()); // Ajout du champ dateOfBirth
                    userResponseDTO.setRoles(user.getRoles().stream()
                            .filter(role -> !"SuperAdmin".equals(role)) // Exclure le rôle "SuperAdmin"
                            .collect(Collectors.toList()));
                    return userResponseDTO;
                })

                .collect(Collectors.toList());
    }



    @GetMapping("/getUserById/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/createUser")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO userRequestDTO) {
        // Hacher le mot de passe avec BCrypt avant de créer l'utilisateur
        String hashedPassword = BCrypt.hashpw(userRequestDTO.getPassword(), BCrypt.gensalt());
        userRequestDTO.setPassword(hashedPassword);  // Mettre à jour l'objet DTO avec le mot de passe haché

        return userService.createUser(userRequestDTO); // Appeler le service pour créer l'utilisateur
    }

    @PutMapping("/updateUser/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        // Hacher le mot de passe avec BCrypt avant de mettre à jour l'utilisateur (si un nouveau mot de passe est fourni)
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isEmpty()) {
            String hashedPassword = BCrypt.hashpw(userRequestDTO.getPassword(), BCrypt.gensalt());
            userRequestDTO.setPassword(hashedPassword);  // Mettre à jour l'objet DTO avec le mot de passe haché
        }

        return userService.updateUser(id, userRequestDTO);
    }


    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/actifs")
    public List<UserResponseDTO> getUtilisateursActifs() {
        return userService.getUtilisateursActifs();
    }





    // Envoi du code de réinitialisation du mot de passe
    @PostMapping("/forgot-password")
    public UserResponseDTO resetForgottenPassword(@RequestBody ResetPasswordRequest request) {
        try {
            // Appeler la méthode du service pour envoyer le code de vérification
            return userService.resetForgottenPassword(request.getEmail());
        } catch (UserNotFoundException e) {
            // Retourner une réponse d'erreur appropriée
            return null;
        }
    }

    // Confirmation de la réinitialisation du mot de passe
    @PostMapping("/confirm-reset-password")
    public ResponseEntity<?> confirmResetPassword(
            @RequestParam String email,
            @RequestParam String verificationCode,
            @RequestParam String newPassword) {
        try {
            UserResponseDTO responseDTO = userService.confirmResetPassword(email, verificationCode, newPassword);
            return ResponseEntity.ok(responseDTO);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (InvalidVerificationCodeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }



//forcer l'utilisateur à changer le mot de passe à la prochaine connexion
@PostMapping("/reset-password")
public ResponseEntity<UserResponseDTO> resetPassword(@RequestBody ForcerPasswordRequest forcerPasswordRequest) {
    try {
        UserResponseDTO response = userService.resetPassword(
                forcerPasswordRequest.getEmail(),
                forcerPasswordRequest.getOldPassword(),
                forcerPasswordRequest.getNewPassword()
        );
        return ResponseEntity.ok(response);
    } catch (UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(null);
    } catch (Exception e) {
        return ResponseEntity.internalServerError().body(null);
    }
}


    @GetMapping("/{enseignantId}/niveaux")
    public ResponseEntity<List<NiveauResponseDTO>> getNiveauxByEnseignant(@PathVariable Long enseignantId) {
        List<NiveauResponseDTO> niveaux = userService.getNiveauxByEnseignant(enseignantId);
        return ResponseEntity.ok(niveaux);
    }
}