package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionResponseDTO {

    private Long id;
    private String permissionName;
    private String permissionDescription;
    private LocalDateTime permissionCreatedAt;
    private LocalDateTime permissionModifiedAt;
    private String permissionCreatedBy;
    private String permissionLastModifiedBy;


    // toString
    @Override
    public String toString() {
        return "PermissionResponseDTO{" +
                "id=" + id +
                ", permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", permissionCreatedAt=" + permissionCreatedAt +
                ", permissionModifiedAt=" + permissionModifiedAt +
                ", permissionCreatedBy='" + permissionCreatedBy + '\'' +
                ", permissionLastModifiedBy='" + permissionLastModifiedBy + '\'' +
                '}';
    }
}