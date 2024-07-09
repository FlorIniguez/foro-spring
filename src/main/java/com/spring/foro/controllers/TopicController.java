package com.spring.foro.controllers;

import com.spring.foro.infra.errors.ResourcedNotFoundException;
import com.spring.foro.models.Topic.*;
import com.spring.foro.utils.TopicUtils;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicService topicService;
    @Autowired
    TopicUtils topicUtils;


    @GetMapping
    public ResponseEntity<Page<ListOfTopics>> topicsList(@PageableDefault(size = 3) Pageable pagination) {
        //configuro orden
        return ResponseEntity.ok(topicRepository.findAllByStatusTrueOrderByCreationDateAsc(pagination).map(topicUtils::mapToListOfTopics));
    }

    @GetMapping("/course/{courseName}")
    //listar por nombre del curso
    public ResponseEntity<Page<ListOfTopics>> listTopicsByCourse(@PathVariable String courseName, Pageable pagination) {
        Page<Topic> topicsPage = topicRepository.findAllByCourseNameIgnoreCase(courseName, pagination);
        Page<ListOfTopics> listOfTopicsPage = topicsPage.map(topicUtils::mapToListOfTopics);
        return ResponseEntity.ok(listOfTopicsPage);
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> topicById(@PathVariable Long id) throws ResourcedNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic", "id", id));

        TopicResponse topicResponse = topicUtils.topicWithResponses(topic);
        return ResponseEntity.ok(topicResponse);
    }

    //ACTUALIZAR UN TOPICO
    @PutMapping("/update/{id}")
    @Transactional
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id, @RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic", "id", id));
        topic.updateTopic(dataUpdateTopic);
    // Guardar los cambios en la base de datos
        topicRepository.save(topic);
       TopicResponse topicResponse = topicUtils.topicWithResponses(topic);
        return ResponseEntity.ok(topicResponse);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> createTopic(@RequestBody @Valid CreateTopic createTopic) {
        Topic response = topicService.create(createTopic);

      TopicResponse topicResponse = topicUtils.topicWithResponses(response);
        return ResponseEntity.ok(topicResponse);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic", "id", id));
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }

}
