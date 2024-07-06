package com.spring.foro.controllers;

import com.spring.foro.models.Course.Course;
import com.spring.foro.models.Course.CourseRepository;
import com.spring.foro.models.Course.CreateCourse;
import com.spring.foro.models.Course.ResponseCourse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
                                                         UriComponentsBuilder uriComponentsBuilder){
        Course course = courseRepository.save(new Course(createCourse));

        ResponseCourse responseCourse = new ResponseCourse(course.getId(),course.getName(),course.getCategory());
        URI url = uriComponentsBuilder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();
        return ResponseEntity.created(url).body(responseCourse);
    }
}
