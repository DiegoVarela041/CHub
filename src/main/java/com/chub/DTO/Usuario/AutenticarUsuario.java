package com.chub.DTO.Usuario;

import jakarta.validation.constraints.NotBlank;

public record AutenticarUsuario(
        @NotBlank(message = "¡El correo electrónico es obligatorio y no puede estar vacío!")
        String correoElectronico,
        @NotBlank(message = "¡La contraseña es obligatoria y no puede estar vacía!")
        String contrasenia) {
}
