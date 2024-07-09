package com.spring.foro.models.Topic;

import com.spring.foro.models.Response.Response;
import com.spring.foro.models.Response.ResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record ListOfTopics(
        Long id,
        String title,
        String content,
        LocalDateTime creationDate,
        String authorName,
        String courseName,
        List<ResponseDTO> listResponses
) {
    public ListOfTopics(Topic topic, List<ResponseDTO> responseDtos) {
        this(topic.getId(), topic.getTitle(), topic.getContent(), topic.getCreationDate(), topic.getAuthor().getUsername(), topic.getCourse().getName(), responseDtos);
    }
}

