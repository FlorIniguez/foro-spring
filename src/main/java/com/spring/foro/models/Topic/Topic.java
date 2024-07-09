package com.spring.foro.models.Topic;

import com.spring.foro.models.Course.Course;
import com.spring.foro.models.Response.Response;
import com.spring.foro.models.User.UserForum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topic")
@Table(name = "topics")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private Boolean status;

    //Muchos topicos puede crear un usuario
    @ManyToOne
    @JoinColumn(name = "userForum_id")
    private UserForum author;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    //un topico puede tener muchas respuestas (relacion creada en response)
    @OneToMany(mappedBy = "topic")
    private List<Response> responses;

    public Topic(CreateTopic createTopic, UserForum author, Course course) {
        this.status = true;
        this.title = createTopic.title();
        this.course = course;
        this.content = createTopic.content();
        this.creationDate = LocalDateTime.now();
        this.author = author;
    }

    public void updateTopic(DataUpdateTopic dataUpdateTopic) {
        if (dataUpdateTopic.title() != null) {
            this.title = dataUpdateTopic.title();
        }
        if (dataUpdateTopic.courseName() != null) {
            this.course = course;
        }
        if (dataUpdateTopic.content() != null) {
            this.content = dataUpdateTopic.content();
        }
        this.setCreationDate(LocalDateTime.now());


    }

    //desactivar topico, para eliminar
    public void deactivateTopic() {
        this.status = false;
    }
}
