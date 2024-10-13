package com.softuni.exercise.SpringBootAndSpringMVC.entity;

import com.softuni.exercise.SpringBootAndSpringMVC.entity.enums.NameOfRole;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private NameOfRole name;
    @ManyToMany(mappedBy = "role")
    @Column(unique = true)
    private List<User> users;

}
