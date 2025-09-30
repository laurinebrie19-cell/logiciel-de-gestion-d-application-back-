package com.stelotechConsulting.gestionMutuel.utilisateur.config;

import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Permission;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Role;
import com.stelotechConsulting.gestionMutuel.utilisateur.model.entities.Utilisateur;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.PermissionRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.RoleRepository;
import com.stelotechConsulting.gestionMutuel.utilisateur.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void initData() {
        // 1. Création des permissions de base
        List<String> permissionsList = Arrays.asList(
                // Gestion des Utilisateurs
                "USER_CREATE", "USER_READ", "USER_UPDATE", "USER_DELETE", "USER_MANAGE_ROLES",

                // Gestion des Rôles
                "ROLE_CREATE", "ROLE_READ", "ROLE_UPDATE", "ROLE_DELETE",

                // Gestion des Permissions
                "PERMISSION_READ",

                // Gestion des Filières
                "FILIERE_CREATE", "FILIERE_READ", "FILIERE_UPDATE", "FILIERE_DELETE",

                // Gestion des Niveaux
                "NIVEAU_CREATE", "NIVEAU_READ", "NIVEAU_UPDATE", "NIVEAU_DELETE",

                // Gestion des Matières
                "MATIERE_CREATE", "MATIERE_READ", "MATIERE_UPDATE", "MATIERE_DELETE",

                // Gestion des Emplois du temps
                "EMPLOI_CREATE", "EMPLOI_READ", "EMPLOI_UPDATE", "EMPLOI_DELETE",

                // Gestion des Salles
                "SALLE_CREATE", "SALLE_READ", "SALLE_UPDATE", "SALLE_DELETE",

                // Gestion des Annonces
                "ANNONCE_CREATE", "ANNONCE_READ", "ANNONCE_UPDATE", "ANNONCE_DELETE",

                // Gestion des Catégories et Types d'annonces
                "CATEGORIE_ANNONCE_CREATE", "CATEGORIE_ANNONCE_READ", "CATEGORIE_ANNONCE_UPDATE", "CATEGORIE_ANNONCE_DELETE",
                "TYPE_ANNONCE_CREATE", "TYPE_ANNONCE_READ", "TYPE_ANNONCE_UPDATE", "TYPE_ANNONCE_DELETE",

                // Gestion des Fonctions de bureau
                "FONCTION_BUREAU_CREATE", "FONCTION_BUREAU_READ", "FONCTION_BUREAU_UPDATE", "FONCTION_BUREAU_DELETE",

                // Gestion des Notifications
                "NOTIFICATION_READ", "NOTIFICATION_UPDATE",

                // Gestion des Étudiants
                "ETUDIANT_CREATE", "ETUDIANT_READ", "ETUDIANT_UPDATE", "ETUDIANT_DELETE",
                // Gestion des notes
                "NOTE_CREATE", "NOTE_READ", "NOTE_READ_ALL"

        );

        // 2. Sauvegarde des permissions
        for (String permName : permissionsList) {
            if (!permissionRepository.existsByPermissionName(permName)) {
                Permission permission = new Permission();
                permission.setPermissionName(permName);
                permissionRepository.save(permission);
            }
        }

        // 3. Création des rôles avec leurs permissions
        // SuperAdmin
        List<Permission> superAdminPerms = permissionRepository.findAll();
        createRoleIfNotExists("SuperAdmin" ,"A tous les droits sur l’application", superAdminPerms);

        // Admin (mêmes droits que SuperAdmin)
        createRoleIfNotExists("Admin","Gère tout l'établissement", superAdminPerms);

        // Enseignant
        List<Permission> enseignantPerms = permissionRepository.findAllByPermissionNameIn(Arrays.asList(
                "USER_READ", "USER_UPDATE", "MATIERE_READ", "MATIERE_UPDATE", "EMPLOI_READ", "EMPLOI_UPDATE", "ANNONCE_READ", "NOTE_CREATE", "NOTE_READ"
        ));
        createRoleIfNotExists("Enseignant","Gère ses cours, notes et emploi du temps", enseignantPerms);

        // Etudiant
        List<Permission> etudiantPerms = permissionRepository.findAllByPermissionNameIn(Arrays.asList(
                "USER_READ", "ETUDIANT_READ", "EMPLOI_READ", "ANNONCE_READ", "NOTE_READ"
        ));
        createRoleIfNotExists("Etudiant","Peut voir ses notes, emploi du temps et annonces", etudiantPerms);

        // 4. Création du SuperAdmin (code existant)
        if (!userRepository.existsByEmail("superadmin@alinfotech.com")) {
            Role superAdminRole = roleRepository.findByRoleName("SuperAdmin").orElseThrow();
            Utilisateur superAdmin = new Utilisateur();
            superAdmin.setFirstName("Super");
            superAdmin.setLastName("Admin");
            superAdmin.setEmail("superadmin@alinfotech.com");
            superAdmin.setPassword(BCrypt.hashpw("SuperAdmin123", BCrypt.gensalt()));
            superAdmin.setAddress("Lélé, Nkongsamba");
            superAdmin.setRoles(Arrays.asList(superAdminRole));
            superAdmin.setRole("SuperAdmin");
            superAdmin.setSex("M");
            superAdmin.setStatus("Actif");

            userRepository.save(superAdmin);
        }
    }



    private void createRoleIfNotExists(String roleName, String roleDescription, List<Permission> permissions) {
        if (!roleRepository.existsByRoleName(roleName)) {
            Role role = new Role();
            role.setRoleName(roleName);
            role.setRoleDescription(roleDescription);
            role.setPermissions(permissions);
            roleRepository.save(role);
        }
    }

}