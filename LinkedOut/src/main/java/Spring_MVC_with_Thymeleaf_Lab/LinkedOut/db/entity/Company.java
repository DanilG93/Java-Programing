package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Positive
    private BigDecimal budget;
    @NotNull
    @Column(columnDefinition = "TEXT")
    private String description;
    @NotNull
    @Column(unique = true)
    private String name;
    @NotNull
    private String town;

    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

}
