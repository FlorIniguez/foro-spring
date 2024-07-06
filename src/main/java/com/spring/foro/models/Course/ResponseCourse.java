package com.spring.foro.models.Course;

public record ResponseCourse(
        Long id,
        String name,
        Category category
) {
}
