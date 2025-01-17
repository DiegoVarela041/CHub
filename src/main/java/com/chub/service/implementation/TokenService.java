package com.chub.service.implementation;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chub.persistence.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    private static final int HORA_EXPIRACION = 2;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("HackHub")
                    .withSubject(usuario.getCorreoElectronico())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaExpiracion(HORA_EXPIRACION))
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error al crear el token JWT", exception);
        }
    }

    private Instant generarFechaExpiracion(int horaExpiracion) {
        return LocalDateTime.now().plusHours(horaExpiracion).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String token) {
        if (token == null) {
            throw new RuntimeException("El token no puede ser nulo");
        }

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            DecodedJWT verifier = JWT.require(algorithm)
                    .withIssuer("HackHub")
                    .build()
                    .verify(token);

            String subject = verifier.getSubject();
            if (subject == null) {
                throw new RuntimeException("Verifier inválido");
            }

            return subject;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Error al verificar el token JWT", exception);
        }
    }
}
