package com.spring.foro.models.Topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    //Listar topicos activos
    Page<Topic> findAllByStatusTrueOrderByCreationDateAsc(Pageable pagination);
    //Corroborar si el topico esta activo
//    @Query("""
//            select t.status
//            from Topic t
//            where t.id=:idTopic
//            """)
//    Boolean findStatusById(Long idTopic);

    //listar por nombre del curso
    @Query("SELECT t FROM Topic t WHERE LOWER(REPLACE(t.course.name, ' ', '')) LIKE LOWER(CONCAT('%', REPLACE(:courseName, ' ', ''), '%'))")
    Page<Topic> findAllByCourseNameIgnoreCase(@Param("courseName") String courseName, Pageable pagination);

    boolean existsByTitleAndStatus(String title,Boolean status);
}
