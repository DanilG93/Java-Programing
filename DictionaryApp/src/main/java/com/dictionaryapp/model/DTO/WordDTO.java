package com.dictionaryapp.model.DTO;
import com.dictionaryapp.model.entity.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WordDTO {
    @NotBlank
    @Size(min = 2,max = 40)
    private String term;
    @NotBlank
    @Size(min = 2,max = 80)
    private String translation;
    @NotBlank
    @Size(min = 2,max = 200)
    private String example;
    @NotNull
    @PastOrPresent
    private LocalDate inputDate;
    @NotNull
    private Language language;

}
