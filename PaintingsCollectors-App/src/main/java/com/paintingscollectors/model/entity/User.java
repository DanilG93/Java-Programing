package com.paintingscollectors.model.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Painting> paintings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "favorite_paintings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "painting_id")
    )
    private List<Painting> favoritePaintings = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "rated_paintings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "painting_id")
    )


    private List<Painting> ratedPaintings = new ArrayList<>();
}

