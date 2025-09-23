package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PermissionRequestDTO {

    private String permissionName;
    private String permissionDescription;
    private LocalDateTime permissionCreatedAt;
    private LocalDateTime permissionModifiedAt;
    private String permissionCreatedBy;
    private String permissionLastModifiedBy;

    // toString
    @Override
    public String toString() {
        return "PermissionRequestDTO{" +
                "permissionName='" + permissionName + '\'' +
                ", permissionDescription='" + permissionDescription + '\'' +
                ", permissionCreatedAt=" + permissionCreatedAt +
                ", permissionModifiedAt=" + permissionModifiedAt +
                ", permissionCreatedBy='" + permissionCreatedBy + '\'' +
                ", permissionLastModifiedBy='" + permissionLastModifiedBy + '\'' +
                '}';
    }
}