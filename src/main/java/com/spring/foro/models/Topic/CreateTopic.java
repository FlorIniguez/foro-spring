package com.spring.foro.models.Topic;

import com.spring.foro.models.Course.Course;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTopic(
        @NotNull
        Long id_user,
        @NotBlank
        String title,
        @NotBlank
        @Valid
        String courseName,
        @NotBlank
        String content) {
    public CreateTopic(Topic topic) {
        this(topic.getAuthor().getId(), topic.getTitle(), topic.getCourse().getName(), topic.getContent());
    }
}
