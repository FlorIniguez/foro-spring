package com.spring.foro.controllers;

import com.spring.foro.models.Topic.*;
import com.spring.foro.models.User.UserForum;
import com.spring.foro.models.User.UserForumRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> createTopic(@RequestBody @Valid CreateTopic createTopic) {

        // Buscar el usuario por ID
//        UserForum author = userForumRepository.findById(createTopic.id_user())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Topic topic = new Topic(createTopic);
//        topic.setAuthor(author);
//        topicRepository.save(topic);
//        TopicResponse response = new TopicResponse(topic.getId(), topic.getTitle(), topic.getContent(), topic.getCreationDate());
//        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
//        return ResponseEntity.created(url).body(response);
        TopicResponse response  = topicService.create(createTopic);
        return ResponseEntity.ok(response);

    }
}
