package com.spring.foro.controllers;

import com.spring.foro.infra.errors.ResourcedNotFoundException;
import com.spring.foro.models.Course.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/courses")
@SecurityRequirement(name = "bearer-key")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public ResponseEntity<ResponseCourse> registerCourse(@RequestBody CreateCourse createCourse,
                                                         UriComponentsBuilder uriComponentsBuilder) {
        Course course = courseRepository.save(new Course(createCourse));

        ResponseCourse responseCourse = new ResponseCourse(course.getId(), course.getName(), course.getCategory());
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(responseCourse);
    }

    @GetMapping
    public ResponseEntity<Page<ListOfCourses>> listOfCourses(Pageable pagination) {
        return ResponseEntity.ok(courseRepository.findAll(pagination).map(ListOfCourses::new));
    }

    @GetMapping("/{idCourse}")
    public ResponseEntity<ResponseCourse> courseForId(@PathVariable Long idCourse) {
        Course course = courseRepository.getReferenceById(idCourse);
        ResponseCourse responseCourse = new ResponseCourse(course.getId(), course.getName(), course.getCategory());
        return ResponseEntity.ok(responseCourse);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateCourse(@PathVariable Long id, @RequestBody @Valid CreateCourse createCourse) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourcedNotFoundException("Course", "id", id));
        ;
        course.updateCourse(createCourse);
        courseRepository.save(course);
        return ResponseEntity.ok(new ResponseCourse(course.getId(), course.getName(), course.getCategory()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long idCourse) {
        Course course = courseRepository.getReferenceById(idCourse);
        courseRepository.deleteById(course.getId());
        return ResponseEntity.noContent().build();
    }
}
