package com.spring.foro.models.Topic;

import com.spring.foro.models.Course.Course;
import com.spring.foro.models.Course.CourseRepository;
import com.spring.foro.models.Response.ResponseDTO;
import com.spring.foro.models.Topic.validations.TopicsValidator;
import com.spring.foro.models.User.UserForum;
import com.spring.foro.models.User.UserForumRepository;
import com.spring.foro.utils.TopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserForumRepository userForumRepository;
    @Autowired
    TopicUtils topicUtils;
    @Autowired
    List<TopicsValidator> validators;

    public Topic  create(CreateTopic createTopic) {

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

        //guardo el topico
       return topicRepository.save(topic);


    }


}



