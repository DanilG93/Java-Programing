package com.bonappetit.model.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDTO {
    @Size(min = 3, max = 20)
    @NotBlank
    private String username;
    @Email
    @NotBlank
    private String email;

    @Size(min = 3, max = 20)
    @NotBlank
    private String password;

    private String confirmPassword;
}
