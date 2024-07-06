package com.spring.foro.models.Topic;

import com.spring.foro.models.Course.Course;
import com.spring.foro.models.Course.CourseRepository;
import com.spring.foro.models.User.UserForum;
import com.spring.foro.models.User.UserForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserForumRepository userForumRepository;

    public TopicResponse create(CreateTopic createTopic) {

        Optional<UserForum> userOptional = userForumRepository.findById(createTopic.id_user());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        UserForum user = userOptional.get();


        Course course = courseRepository.findByName(createTopic.courseName());
        if (course == null) {
            throw new RuntimeException("Course not found");
        }
        //creo el topico
        Topic topic = new Topic(createTopic, user, course);

//        topic.setAuthor(user);
//        topic.setTitle(createTopic.title());
//        topic.setCourse(course);
//        topic.setContent(createTopic.content());
//guardo el topico
        topicRepository.save(topic);

        return new TopicResponse(topic.getId(), topic.getTitle(), topic.getContent(), topic.getCreationDate());
    }

}

