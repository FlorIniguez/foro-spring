package com.spring.foro.models.Topic.validations;

import com.spring.foro.models.Topic.CreateTopic;
import com.spring.foro.models.Topic.Topic;
import com.spring.foro.models.Topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveTopic implements TopicsValidator {
    @Autowired
    private TopicRepository topicRepository;
    @Override
    public void validate(CreateTopic data) {
        boolean activeTopic = topicRepository.existsByTitleAndStatus(data.title(), true);
        if (activeTopic){
            throw new ValidationException("A topic with the title '" + data.title() + "' already exists and is active.");
        }

    }
}
