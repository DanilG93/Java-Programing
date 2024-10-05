package com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities;

import com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.ORDINAL)
    @Column(nullable = false)
    private Role role;

    public UserRole(Role role) {
        this.role = role;
    }
}
