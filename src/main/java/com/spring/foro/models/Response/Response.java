package com.spring.foro.models.Response;

import com.spring.foro.models.Topic.Topic;
import com.spring.foro.models.User.UserForum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Response")
@Table(name = "responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    //muchas respuestas a un topico
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    private LocalDateTime creationDate;
    //muchas respuestas de un usuario
    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserForum author;
    private Boolean solution;

    public Response(CreateResponse createResponse, UserForum author, Topic topic) {
        this.content = createResponse.content();
        this.topic = topic;
        this.creationDate = LocalDateTime.now();
        this.author = author;
        this.solution = true;
    }
    //actualizar

    //desactivar
    public void solutionResponse() {
        this.solution = false;
    }

    public void updateResponse(DataUpdateResponse data) {

        if (data.idTopic() != null) {
            this.topic = topic;
        }
        if (data.content() != null) {
            this.content = data.content();
        }
        this.setCreationDate(LocalDateTime.now());

    }
}
