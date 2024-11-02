package com.softuni.exercise.SpringBootAndSpringMVC.db.entity;

import com.softuni.exercise.SpringBootAndSpringMVC.db.entity.enums.NameOfRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
