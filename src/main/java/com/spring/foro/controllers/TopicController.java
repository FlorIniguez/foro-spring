package com.spring.foro.controllers;

import com.spring.foro.infra.errors.ResourcedNotFoundException;
import com.spring.foro.models.Topic.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping
    @Transactional
    public ResponseEntity<TopicResponse> createTopic(@RequestBody @Valid CreateTopic createTopic) {
        TopicResponse response = topicService.create(createTopic);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<ListOfTopics>> topicsList(Pageable pagination) {
        //configuro orden
        return ResponseEntity.ok(topicRepository.findAllByStatusTrueOrderByCreationDateAsc(pagination).map(ListOfTopics::new));
    }

    @GetMapping("/course/{courseName}")
    //listar por nombre del curso
    public ResponseEntity<Page<ListOfTopics>> listTopicsByCourse(@PathVariable String courseName, Pageable pagination) {
        Page<Topic> topicsPage = topicRepository.findAllByCourseNameIgnoreCase(courseName, pagination);
        Page<ListOfTopics> listOfTopicsPage = topicsPage.map(ListOfTopics::new);
        return ResponseEntity.ok(listOfTopicsPage);
    }

    //BUSCAR POR ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicResponse> topicById(@PathVariable Long id) throws ResourcedNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic", "id", id));

        TopicResponse topicResponse = new TopicResponse(topic.getId(), topic.getTitle(), topic.getContent(), topic.getAuthor().getUsername(), topic.getCourse().getName(), topic.getCreationDate());
        return ResponseEntity.ok(topicResponse);
    }

    //ACTUALIZAR UN TOPICO
    @PutMapping("/update/{id}")
    public ResponseEntity<TopicResponse> updateTopic(@PathVariable Long id, @RequestBody @Valid DataUpdateTopic dataUpdateTopic) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Topic", "id", id));
        topic.updateTopic(dataUpdateTopic);
// Guardar los cambios en la base de datos
        topicRepository.save(topic);
        return ResponseEntity.ok(new TopicResponse(topic.getId(), topic.getTitle(), topic.getContent(), topic.getAuthor().getUsername(),
                topic.getCourse().getName(), topic.getCreationDate()));
    }
@DeleteMapping("/delete/{id}")
@Transactional
   public  ResponseEntity deleteTopic(@PathVariable Long id){
    Topic topic = topicRepository.findById(id)
            .orElseThrow(() -> new ResourcedNotFoundException("Topic", "id", id));
    topic.deactivateTopic();
    return ResponseEntity.noContent().build();
}

}
