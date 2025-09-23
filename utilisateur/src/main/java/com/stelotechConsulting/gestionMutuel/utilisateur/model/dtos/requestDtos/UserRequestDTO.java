package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private String sex;
    private Date dateOfBirth;
    private String createdBy;
    private String lastModifiedBy;
    private List<Long> roleIds;
    private String status;

    private String oldPassword;
    private String newPassword;
    private String verificationCode;
    //  Si l’utilisateur est du bureau
    private Long fonctionBureauId;

    // L’acteur qui crée l’utilisateur (on utilisera son email pour check habilitation)
    private String actorEmail;

    // toString
    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' + // Mot de passe inclus dans les logs
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", roleIds=" + roleIds +
                ", status='" + status + '\'' +
                '}';
    }
}