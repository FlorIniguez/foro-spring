package com.spring.foro.models.Response;

import com.spring.foro.models.Topic.Topic;
import com.spring.foro.models.User.UserForum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Response")
@Table(name = "responses")
@Data
@NoArgsConstructor
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

}
