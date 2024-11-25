package com.bonappetit.model.DTO;

import com.bonappetit.model.entity.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddRecipeDTO {

    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 40)
    private String name;

    @NotBlank(message = "Ingredients are required.")
    @Size(min = 2,max = 150)
    private String ingredients;

    @NotNull(message = "Category is required.")
    private Category category;

}
