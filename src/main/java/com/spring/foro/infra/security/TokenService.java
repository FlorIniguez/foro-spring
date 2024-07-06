package com.spring.foro.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.spring.foro.models.User.UserForum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.secret}")
    private String apiSecret;

    public String generateToken(UserForum user) {

        try {
            //secret contraseña para validar la firma
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            //va a crear un jwt issuer es el que emite el token
            return JWT.create()
                    .withIssuer("foroHub")
                    //va dirigido
                    .withSubject(user.getUsername())
                    .withClaim("id", user.getId())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException();
        }

    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("Token is null");
        }
        //copio del repo de github de jwt y cambio a mis datos el tipo de algoritmo
        //el Issuer
        //ver si el usuario ha iniciado sesion
        //DecodedJWT verifier = null;
        try {
            //valido firma del token
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("foroHub")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
                    //.verify(token);
            //verifier.getSubject();

        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
            throw new RuntimeException("Invalid JWT", exception);
        }

//        if (verifier.getSubject() == null) {
//            throw new RuntimeException("Verifier invalid");
//        }
//        return verifier.getSubject();
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }
}
