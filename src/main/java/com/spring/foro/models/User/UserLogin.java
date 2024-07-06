package com.spring.foro.models.User;

public record UserLogin(
        String email,
        String username,
        String password
) {
}
