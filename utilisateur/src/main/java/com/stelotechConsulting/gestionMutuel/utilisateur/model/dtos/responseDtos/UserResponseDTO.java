package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

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
public class UserResponseDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String phoneNumber;
    private String email;
    private String address;
    private String sex;
    private Date dateOfBirth;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String createdBy;
    private String lastModifiedBy;
    private List<String> roles;
    private List<String> permissions;
    private String status;
    private Boolean mustChangePassword;
    private boolean valid;

    private String oldPassword;
    private String newPassword;
    private String verificationCode;
    private Long fonctionBureauId;

    // toString
    @Override
    public String toString() {
        return "UserResponseDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", status='" + status + '\'' +
                ", permissions=" + permissions +
                ", roles=" + roles +
                ", fonctionBureauId=" + fonctionBureauId +
                '}';
    }
}
