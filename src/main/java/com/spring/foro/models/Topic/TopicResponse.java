package com.spring.foro.models.Topic;

import com.spring.foro.models.Response.Response;
import com.spring.foro.models.Response.ResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public record TopicResponse(
        Long topic_id,
        String content,
        String authorName,
        String courseName,
        LocalDateTime creationDate,
        List<ResponseDTO> listResponses) {

    public TopicResponse(Topic topic, List<ResponseDTO> responseDtos) {
        this(topic.getId(),  topic.getContent(), topic.getAuthor().getUsername(), topic.getCourse().getName(),topic.getCreationDate(), responseDtos);
    }
}

