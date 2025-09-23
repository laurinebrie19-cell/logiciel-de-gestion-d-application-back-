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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "role")
@EntityListeners(AuditingEntityListener.class) // Active l'audit automatique
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleName;
    private String roleDescription;
    @CreatedDate
    @Column(name = "roleCreatedAt", updatable = false, nullable = false)
    protected LocalDateTime roleCreatedAt;

    @LastModifiedDate
    @Column(name = "roleModifiedAt", nullable = false)
    protected LocalDateTime roleModifiedAt;
    @CreatedBy
    @Column(name = "roleCreatedBy", updatable = false/*nullable = false*/)
    protected String roleCreatedBy;
    @LastModifiedBy
    @Column(name = "roleLastModifiedBy"/*nullable = false*/)
    protected String roleLastModifiedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private List<Permission> permissions = new ArrayList<>();

    public Role(long l, String roleName) {
        this.id = l ;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(roleName, role.roleName) &&
                Objects.equals(roleDescription, role.roleDescription) &&
                Objects.equals(roleCreatedAt, role.roleCreatedAt) &&
                Objects.equals(roleModifiedAt, role.roleModifiedAt) &&
                Objects.equals(roleCreatedBy, role.roleCreatedBy) &&
                Objects.equals(roleLastModifiedBy, role.roleLastModifiedBy) &&
                Objects.equals(permissions, role.permissions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, roleDescription, roleCreatedAt, roleModifiedAt, roleCreatedBy, roleLastModifiedBy, permissions);
    }

    // toString
    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", roleCreatedAt=" + roleCreatedAt +
                ", roleModifiedAt=" + roleModifiedAt +
                ", roleCreatedBy='" + roleCreatedBy + '\'' +
                ", roleLastModifiedBy='" + roleLastModifiedBy + '\'' +
                ", permissions=" + permissions +
                '}';
    }
}