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
                "USER_CREATE", "USER_READ", "USER_UPDATE", "USER_DELETE", "USER_MANAGE_ROLES","ADD_MEMBRE_BUREAU",

                // Gestion des Sessions
                "SESSION_CREATE", "SESSION_READ", "SESSION_UPDATE", "SESSION_DELETE",
                "SESSION_CLOSE", "SESSION_MANAGE_BALANCE" ,"READ_SOLDE_SESSION",

                // Gestion des Périodes
                "PERIOD_CREATE", "PERIOD_READ", "PERIOD_UPDATE", "PERIOD_DELETE",
                "PERIOD_TERMINATE", "PERIOD_MANAGE_STATUS",

                // Gestion des Cotisations
                "CONTRIBUTION_CREATE", "CONTRIBUTION_READ", "CONTRIBUTION_UPDATE",
                "CONTRIBUTION_DELETE", "CONTRIBUTION_REPORT",

                // permissions pour les statistiques
                "STATISTICS_VIEW", "STATISTICS_EXPORT",

                // Gestion des Emprunts
                "LOAN_CREATE", "LOAN_READ", "LOAN_UPDATE", "LOAN_DELETE",
                "LOAN_APPROVE", "LOAN_REJECT", "LOAN_CANCEL", "LOAN_MANAGE",

                // Gestion des Remboursements
                "REPAYMENT_CREATE", "REPAYMENT_READ", "REPAYMENT_MANAGE",
                "REPAYMENT_TRACK_DEBTS", "REPAYMENT_MANAGE_INTEREST",

                "UPDATE_MODELE_EN_TETE", // Pour paramétrer le modèle en tête
                "BUREAU_MANAGE", // pour créer les fonction bureau
                "ADD_MEMBRE_BUREAU"
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

        // Admin
        List<Permission> adminPerms = permissionRepository.findAllByPermissionNameIn(Arrays.asList(
                "USER_READ","USER_CREATE", "USER_UPDATE", "USER_MANAGE_ROLES",
                "SESSION_CREATE", "SESSION_READ", "SESSION_UPDATE", "SESSION_CLOSE",
                "PERIOD_CREATE", "PERIOD_READ", "PERIOD_UPDATE", "PERIOD_DELETE",
                "PERIOD_TERMINATE", "PERIOD_MANAGE_STATUS",
                "CONTRIBUTION_READ", "CONTRIBUTION_REPORT",
                "LOAN_APPROVE", "LOAN_REJECT", "LOAN_READ","LOAN_CREATE",
                "STATISTICS_VIEW", "STATISTICS_EXPORT",
                "REPAYMENT_READ", "LOAN_MANAGE",
                "CONTRIBUTION_CREATE", "CONTRIBUTION_READ", "CONTRIBUTION_UPDATE","CONTRIBUTION_REPORT","READ_SOLDE_SESSION",
                "UPDATE_MODELE_EN_TETE","BUREAU_MANAGE","ADD_MEMBRE_BUREAU"
        ));
        createRoleIfNotExists("Admin","Gère les utilisateurs, les sessions et les rapports", adminPerms);




        // Trésorier
        List<Permission> tresorierPerms = permissionRepository.findAllByPermissionNameIn(Arrays.asList(
                "USER_READ","USER_CREATE", "USER_UPDATE",
                "CONTRIBUTION_CREATE", "CONTRIBUTION_READ", "CONTRIBUTION_UPDATE","CONTRIBUTION_REPORT",
                "SESSION_CREATE", "SESSION_READ","SESSION_UPDATE", "SESSION_CLOSE", "SESSION_MANAGE_BALANCE",
                "PERIOD_CREATE", "PERIOD_READ", "PERIOD_UPDATE", "PERIOD_MANAGE_STATUS",
                "LOAN_READ", "LOAN_APPROVE", "LOAN_REJECT","LOAN_CREATE",
                "STATISTICS_VIEW", "STATISTICS_EXPORT",
                "REPAYMENT_CREATE", "REPAYMENT_READ", "REPAYMENT_MANAGE",

                "REPAYMENT_TRACK_DEBTS", "REPAYMENT_MANAGE_INTEREST", "LOAN_MANAGE","READ_SOLDE_SESSION"
        ));
        createRoleIfNotExists("Enseignant","Gère les cotisations, emprunts et remboursements", tresorierPerms);

        // Membre
        List<Permission> membrePerms = permissionRepository.findAllByPermissionNameIn(Arrays.asList(
                "CONTRIBUTION_READ","CONTRIBUTION_CREATE",
                "LOAN_CREATE", "LOAN_READ", "LOAN_CANCEL",
                "REPAYMENT_READ", "USER_READ","REPAYMENT_CREATE"
        ));
        createRoleIfNotExists("Etudiant","Peut consulter ses cotisations et faire des demandes de prêt", membrePerms);

        // 4. Création du SuperAdmin (code existant)
        if (!userRepository.existsByEmail("superadmin@alinfotech.com")) {
            Role superAdminRole = roleRepository.findByRoleName("SuperAdmin").orElseThrow();
            Utilisateur superAdmin = new Utilisateur();
            superAdmin.setFirstName("Super");
            superAdmin.setLastName("Admin");
            superAdmin.setEmail("superadmin@alinfotech.com");
            superAdmin.setPassword(BCrypt.hashpw("SuperAdmin123", BCrypt.gensalt()));
            superAdmin.setAddress("123 Rue Principale");
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