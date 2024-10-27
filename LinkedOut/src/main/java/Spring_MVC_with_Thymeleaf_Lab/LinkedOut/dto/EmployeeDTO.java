package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.dto;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.db.entity.Company;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotNull(message = "The field must not be empty")
    @Size(min = 2, message = "The length of the description must be minimum 2")
    private String firstName;

    @NotNull(message = "The field must not be empty")
    @Size(min = 2, message = "The length of the description must be minimum 2")
    private String lastName;

    @NotNull(message = "Choose education Level... please!!!!")
    private String educationLevel;

    @NotNull(message = "The field must not be empty")
    private Company company;

    @NotNull(message = "The field must not be empty")
    private String jobTitle;

    @NotNull(message = "The field must not be empty")
    private LocalDate birthDate;

    @NotNull(message = "The field must not be empty")
    @Positive(message = "Must be a positive number")
    private BigDecimal salary;
}
