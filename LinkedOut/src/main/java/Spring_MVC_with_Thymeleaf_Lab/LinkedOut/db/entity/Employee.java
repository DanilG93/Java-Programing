package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @NotNull
    @Column(name = "education_level")
    private String educationLevel;
    @NotNull
    @Size(min = 2)
    @Column(name = "first_name")
    private String firstName;
    @NotNull
    @Column(name = "job_title")
    private String jobTitle;
    @NotNull
    @Size(min = 2)
    @Column(name = "last_name")
    private String lastName;
    @NotNull
    @Positive
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "company_id",referencedColumnName = "id")
    private Company company;
}
