package com.dictionaryapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 20)
    private String username;

    @Column(nullable = false)
    @Size(min = 3, max = 20)
    private String password;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL)
    private Set<Word> addedWords = new HashSet<>();


}
