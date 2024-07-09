package com.spring.foro.models.Response;

import java.time.LocalDateTime;

public record ResponseDTO(
        Long id,
        String authorName,
        String topicName,
        String content,
        LocalDateTime creationDate
){
    public ResponseDTO(Response response) {
        this(response.getId(), response.getAuthor().getUsername(), response.getTopic().getTitle(),response.getContent(),response.getCreationDate());
    }
}
