package com.chub.DTO.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrarUsuario(
        @NotBlank(message = "¡El nombre del usuario es obligatorio y no puede estar vacío!")
        String nombre,
        @NotBlank(message = "¡El correo electrónico es obligatorio y no puede estar vacío!")
        @Email(message = "¡Debe proporcionar una dirección de correo electrónico válida!")
        String correoElectronico,
        @NotBlank(message = "¡La contraseña es obligatoria y no puede estar vacía!")
        String contrasenia) {
}
