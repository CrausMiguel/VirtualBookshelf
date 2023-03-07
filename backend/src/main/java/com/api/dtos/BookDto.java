package com.api.dtos;

import com.api.models.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor

public class BookDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    private int pagesNumber;
    @NotNull
    private Set<Gender> genders;
}
