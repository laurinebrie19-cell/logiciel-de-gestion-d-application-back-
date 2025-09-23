package com.stelotechConsulting.gestionMutuel.utilisateur.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import org.mindrot.jbcrypt.BCrypt; // Utiliser BCrypt directement

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "utilisateurs")
@EntityListeners(AuditingEntityListener.class) // Active l'audit automatique
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false)
    private String firstName;

    private String lastName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "phoneNumber", unique = false, nullable = true)
    private String phoneNumber;

    @Column(name = "email", unique = false, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "sex", nullable = false)
    private String sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @CreatedDate
    @Column(name = "createdAt", updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modifiedAt", nullable = false)
    protected LocalDateTime modifiedAt;

    @CreatedBy
    @Column(name = "createdBy", updatable = false)
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "lastModifiedBy")
    protected String lastModifiedBy;

    @Column(name = "status", nullable = false)
    private String status = "Actif";

    @Column(name = "valid", nullable = false)
    private boolean valid = true;



    @Column(name = "must_change_password")
    private Boolean mustChangePassword = false;


    @Column(name = "oldpassword")
    private String oldPassword;
    @Column(name = "newpassword")
    private String newPassword;

    @Column
    private String verificationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> roles = new ArrayList<>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fonction_bureau_id")
    private FonctionBureau fonctionBureau;


    // Equals et HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilisateur that = (Utilisateur) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(role, that.role) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(address, that.address) &&
                Objects.equals(sex, that.sex) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(modifiedAt, that.modifiedAt) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(lastModifiedBy, that.lastModifiedBy) &&
                Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, role, phoneNumber, email, password, address, sex, dateOfBirth, createdAt, modifiedAt, createdBy, lastModifiedBy, roles);
    }

    // toString
    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" + // Sécurité : ne pas afficher le mot de passe en clair
                ", address='" + address + '\'' +
                ", sex='" + sex + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", modifiedAt=" + modifiedAt +
                ", createdBy='" + createdBy + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", roles=" + roles +
                ", status='" + status + '\'' +
                ", valid=" + valid +
                '}';
    }
}