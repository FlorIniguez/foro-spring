package com.spring.foro.controllers;

import com.spring.foro.infra.errors.ResourcedNotFoundException;
import com.spring.foro.models.Response.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "bearer-key")
public class ResponseController {
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    private ResponseService responseService;

    @PostMapping
    @Transactional
    public ResponseEntity<ResponseDTO> createResponse(@RequestBody @Valid CreateResponse createResponse) {
        ResponseDTO responseDto = responseService.create(createResponse);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{idResponse}")
    @Transactional
    public ResponseEntity<ResponseDTO> updateResponse(@PathVariable Long idResponse, @RequestBody @Valid DataUpdateResponse dataUpdateResponse) {
        Response response = responseRepository.findById(idResponse)
                .orElseThrow(() -> new ResourcedNotFoundException("Response", "id", idResponse));
        response.updateResponse(dataUpdateResponse);
        responseRepository.save(response);
        return ResponseEntity.ok(new ResponseDTO(response.getId(), response.getAuthor().getUsername(), response.getTopic().getTitle(), response.getContent(), response.getCreationDate()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        responseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
