package com.bonappetit.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;



@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    @Length(min = 2, max = 40)
    private String name;

    @Column(nullable = false, length = 150)
    @Length(min = 2, max = 150)
    private String ingredients;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User addedBy;

}
