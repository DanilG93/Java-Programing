package com.softuniLab.MobiLeLeLeWebApplication.dataBase.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name",unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(name = "is_active",nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id",nullable = false)
    private UserRole role;
    @Column(name = "image_url",nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private LocalDateTime created;
    @Column(nullable = false)
    private LocalDateTime modified;


    public User(String username, String password,
                String firstName, String lastName,
                Boolean isActive, UserRole role, String imageUrl,
                LocalDateTime created, LocalDateTime modified) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.role = role;
        this.imageUrl = imageUrl;
        this.created = created;
        this.modified = modified;
    }
}
