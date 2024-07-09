package com.spring.foro.models.Response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateResponse(
        @NotNull
        Long idUser,
        @NotNull
        Long idTopic,
        @NotBlank
        String content

) {
    public CreateResponse(Response response){
        this(response.getAuthor().getId(),response.getTopic().getId(),response.getContent());
    }
}
