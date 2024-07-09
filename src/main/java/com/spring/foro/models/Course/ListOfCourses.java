package com.spring.foro.models.Course;

public record ListOfCourses(
        Long idCourse,
        String name,
        Category category
) {
    public ListOfCourses(Course course){
        this(course.getId(),course.getName(),course.getCategory());
    }
}
