package com.spring.foro.models.Course;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    // Constructor que inicializa Course desde CreateCourse
    public Course(CreateCourse createCourse) {
        this.name = createCourse.name();
        this.category = createCourse.category();
    }
    public Course updateCourse(CreateCourse createCourse){
        this.name = createCourse.name();
        this.category = createCourse.category();
        return this;

    }
}

