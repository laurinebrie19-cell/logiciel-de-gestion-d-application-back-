package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "permission")
@EntityListeners(AuditingEntityListener.class) // Active l'audit automatique
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String permissionName;
    private String permissionDescription;
    @CreatedDate
    @Column(name = "permissionCreatedAt", updatable = false, nullable = false)
    protected LocalDateTime permissionCreatedAt;

    @LastModifiedDate
    @Column(name = "permissionModifiedAt", nullable = false)
    protected LocalDateTime permissionModifiedAt;
    @CreatedBy
    @Column(name = "permissionCreatedBy", updatable = false/*nullable = false*/)
    protected String permissionCreatedBy;
    @LastModifiedBy
    @Column(name = "permissionLastModifiedBy"/*nullable = false*/)
    protected String permissionLastModifiedBy;

    public String getPermissionName() {
        return this.permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }


    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(permissionName, that.permissionName) &&
                Objects.equals(permissionDescription, that.permissionDescription) &&
                Objects.equals(permissionCreatedAt, that.permissionCreatedAt) &&
                Objects.equals(permissionModifiedAt, that.permissionModifiedAt) &&
                Objects.equals(permissionCreatedBy, that.permissionCreatedBy) &&
                Objects.equals(permissionLastModifiedBy, that.permissionLastModifiedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, permissionName, permissionDescription, permissionCreatedAt, permissionModifiedAt, permissionCreatedBy, permissionLastModifiedBy);
    }

    // toString
    @Override
    public String toString() {
        return "Permission{" +
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