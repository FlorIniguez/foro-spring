package com.spring.foro.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserForumRepository extends JpaRepository<UserForum, Long> {
    UserDetails findByUsername(String username);
}
