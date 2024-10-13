package com.softuni.exercise.SpringBootAndSpringMVC.entity;

import com.softuni.exercise.SpringBootAndSpringMVC.entity.enums.Level;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int age;
    @Column(name = "full_name")
    private String fullName;
    private String email;
    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(255)")
    private Level level;

    @Column(name = "username", nullable = false)
    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    @Column(unique = true)
    private List<Role> role;


    @OneToMany(mappedBy = "author")
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    private List<Route> routes;


    @OneToMany(mappedBy = "author")
    private List<Message> messages_authors;
    @OneToMany(mappedBy = "recipient")
    private List<Message> messages_recipients;

    @OneToMany(mappedBy = "user")
    private List<Picture> pictures;

}
