package com.chub.DTO.token;

public record DatosJWTtoken(String jwToken) {
    public DatosJWTtoken {
        if (jwToken == null || jwToken.isEmpty()) {
            throw new IllegalArgumentException("¡El token JWT no puede estar vacío!");
        }
    }
}
