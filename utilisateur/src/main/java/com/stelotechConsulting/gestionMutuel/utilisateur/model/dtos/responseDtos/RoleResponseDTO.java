package com.stelotechConsulting.gestionMutuel.utilisateur.model.dtos.responseDtos;

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
public class RoleResponseDTO {

    private Long id;
    private String roleName;
    private String roleDescription;
    private List<PermissionResponseDTO> permissions;

    // toString
    @Override
    public String toString() {
        return "RoleResponseDTO{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
               /* ", roleDescription='" + roleDescription + '\'' +
                ", roleCreatedAt=" + roleCreatedAt +
                ", roleModifiedAt=" + roleModifiedAt +
                ", roleCreatedBy='" + roleCreatedBy + '\'' +
                ", roleLastModifiedBy='" + roleLastModifiedBy + '\'' +*/
                ", permissions=" + permissions +
                '}';
    }
}