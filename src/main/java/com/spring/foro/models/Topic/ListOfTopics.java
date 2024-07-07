package com.spring.foro.models.Topic;

import com.spring.foro.models.Course.Course;
import com.spring.foro.models.User.UserForum;

import java.time.LocalDateTime;

public record ListOfTopics (
        Long id,
       String title,
       String content,
       LocalDateTime creationDate,
       Boolean status,
       String authorName,
       String courseName
){
    public ListOfTopics(Topic topic){
        this(topic.getId(), topic.getTitle(),topic.getContent(),topic.getCreationDate(),topic.getStatus(),topic.getAuthor().getUsername(),topic.getCourse().getName());
    }
}
