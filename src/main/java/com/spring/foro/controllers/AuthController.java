package com.spring.foro.controllers;

import com.spring.foro.infra.security.JWTTokenDto;
import com.spring.foro.infra.security.TokenService;
import com.spring.foro.models.User.UserForum;
import com.spring.foro.models.User.UserLogin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticationUser(@RequestBody @Valid UserLogin userLogin) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(userLogin.username(), userLogin.password());
        var authenticatedUser = authenticationManager.authenticate(authToken);
        //getPrincipal es el usuario autenticado
        var JWTtoken = tokenService.generateToken((UserForum) authenticatedUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDto(JWTtoken));
    }
}

