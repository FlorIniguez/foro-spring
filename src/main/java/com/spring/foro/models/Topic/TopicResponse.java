package com.spring.foro.models.Topic;

import com.spring.foro.models.Course.Course;

import java.time.LocalDateTime;

public record TopicResponse(
        Long topic_id,
        String title,
        String content,
        String authorName,
        String courseName,
        LocalDateTime creationDate) {


}

