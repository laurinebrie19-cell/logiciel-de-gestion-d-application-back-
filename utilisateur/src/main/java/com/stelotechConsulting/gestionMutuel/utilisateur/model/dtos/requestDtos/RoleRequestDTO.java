package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.requestDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleRequestDTO {

    private String roleName;
    private String roleDescription;
    private LocalDateTime roleCreatedAt;
    private LocalDateTime roleModifiedAt;
    private String roleCreatedBy;
    private String roleLastModifiedBy;
    private List<Long> permissionIds;


    // toString
    @Override
    public String toString() {
        return "RoleRequestDTO{" +
                "roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleCreatedAt=" + roleCreatedAt +
                ", roleModifiedAt=" + roleModifiedAt +
                ", roleCreatedBy='" + roleCreatedBy + '\'' +
                ", roleLastModifiedBy='" + roleLastModifiedBy + '\'' +
                ", permissionIds=" + permissionIds +
                '}';
    }
}