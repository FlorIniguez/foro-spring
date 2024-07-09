package com.spring.foro.utils;

import com.spring.foro.models.Response.ResponseDTO;
import com.spring.foro.models.Topic.ListOfTopics;
import com.spring.foro.models.Topic.Topic;
import com.spring.foro.models.Topic.TopicResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
//-------- UN TOPICO ------------
public class TopicUtils {
    public TopicResponse topicWithResponses(Topic topic) {
        List<ResponseDTO> responseDTOs = (topic.getResponses() != null) ?
                topic.getResponses().stream()
                        .map(response -> new ResponseDTO(
                                response.getId(),
                                response.getAuthor().getUsername(),
                                topic.getTitle(),
                                response.getContent(),
                                response.getCreationDate()
                        ))
                        .collect(Collectors.toList())
                : Collections.emptyList();

        return new TopicResponse(
                topic.getId(),
                topic.getContent(),
                topic.getAuthor().getUsername(),
                topic.getCourse().getName(),
                topic.getCreationDate(),
                responseDTOs
        );
    }

    //---------- LISTA DE TOPICOS -----------
    public ListOfTopics mapToListOfTopics(Topic topic) {
        List<ResponseDTO> responseDTOs = (topic.getResponses() != null) ?
                topic.getResponses().stream()
                        .map(response -> new ResponseDTO(
                                response.getId(),
                                response.getAuthor().getUsername(),
                                topic.getTitle(),
                                response.getContent(),
                                response.getCreationDate()
                        ))
                        .collect(Collectors.toList())
                : Collections.emptyList();
        return new ListOfTopics(
                topic.getId(),
                topic.getTitle(),
                topic.getContent(),
                topic.getCreationDate(),
                topic.getAuthor().getUsername(),
                topic.getCourse().getName(),
                responseDTOs
        );

    }


}