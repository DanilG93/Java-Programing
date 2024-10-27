package Spring_MVC_with_Thymeleaf_Lab.LinkedOut.dto;

import Spring_MVC_with_Thymeleaf_Lab.LinkedOut.util.UniqueName;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CompanyDTO {

    @NotNull(message = "The field must not be empty")
    @Size(min = 2, max = 10,message = "The length of the name must be between 2 and 10")
    @UniqueName(message = "Company already exist!!")
    private String name;

    @NotNull(message = "The field must not be empty")
    @Size(min = 2, max = 10,message = "The length of the town must be between 2 and 10")
    private String town;

    @NotNull(message = "The field must not be empty")
    @Size(min = 10, message = "The length of the description must be minimum 10")
    private String description;

    @NotNull(message = "The field must not be empty")
    @Positive(message = "Must be a positive number")
    private BigDecimal budget;

}
