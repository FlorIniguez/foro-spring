package com.spring.foro.models.Topic;

import jakarta.validation.constraints.NotNull;

public record DataUpdateTopic(
//        @NotNull
//        Long idTopic,
        String title,
        String courseName,
        String content

) {
}
