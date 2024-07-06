package com.spring.foro.models.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course,Long> {
    //@Query("select c from Course c where c.name=:courseName")
    Course findByName(String name);
}
