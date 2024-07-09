package com.spring.foro.models.Response;

import com.spring.foro.models.Topic.Topic;
import com.spring.foro.models.Topic.TopicRepository;
import com.spring.foro.models.Topic.validations.TopicsValidator;
import com.spring.foro.models.User.UserForum;
import com.spring.foro.models.User.UserForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponseService {
    @Autowired
    ResponseRepository responseRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    UserForumRepository userForumRepository;
    @Autowired
    List<TopicsValidator> validators;

    public ResponseDTO create(CreateResponse createResponse){
        //-------- AUTHOR -----------
        Optional<UserForum> userOptional = userForumRepository.findById(createResponse.idUser());
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        UserForum user = userOptional.get();
        // ------ TOPIC ---------
        Optional<Topic> optionalTopic = topicRepository.findById(createResponse.idTopic());
        if (optionalTopic.isEmpty()){
            throw new RuntimeException("Topic not found");
        }
        Topic topic = optionalTopic.get();
       Response response = new Response(createResponse,user,topic);
       responseRepository.save(response);

       return new ResponseDTO(response.getId(), response.getAuthor().getUsername(),response.getTopic().getTitle(),response.getContent(),response.getCreationDate());
    }

}
