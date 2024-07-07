package com.spring.foro.utils;

import com.spring.foro.models.Course.Course;
import com.spring.foro.models.Course.CourseRepository;
import com.spring.foro.models.Topic.CreateTopic;
import com.spring.foro.models.Topic.Topic;
import com.spring.foro.models.Topic.TopicRepository;
import com.spring.foro.models.Topic.TopicResponse;
import com.spring.foro.models.Topic.validations.TopicsValidator;
import com.spring.foro.models.User.UserForum;
import com.spring.foro.models.User.UserForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TopicUtils {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserForumRepository userForumRepository;
    @Autowired
    List<TopicsValidator> validators;

    public TopicResponse convertCreateToResponse(CreateTopic createTopic){
        Optional<UserForum> userOptional = userForumRepository.findById(createTopic.id_user());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        UserForum user = userOptional.get();


        Course course = courseRepository.findByName(createTopic.courseName());
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        //forEach de las validaciones creadas
        validators.forEach(v -> v.validate(createTopic));
        //creo el topico
        Topic topic = new Topic(createTopic, user, course);
        return new TopicResponse(topic.getId(), topic.getTitle(), topic.getContent(), topic.getAuthor().getUsername(), topic.getCourse().getName(), topic.getCreationDate());

    }
}
