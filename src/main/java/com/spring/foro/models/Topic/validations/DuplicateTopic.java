package com.spring.foro.models.Topic.validations;

import com.spring.foro.models.Topic.CreateTopic;
import org.springframework.stereotype.Component;

@Component
public class DuplicateTopic implements TopicsValidator{
    @Override
    public void validate(CreateTopic data) {

    }
}
