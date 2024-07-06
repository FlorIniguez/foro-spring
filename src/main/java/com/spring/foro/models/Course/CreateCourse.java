package com.spring.foro.models.Course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCourse(
        @NotBlank
        String name,
        @NotNull
        Category category
) {
}
