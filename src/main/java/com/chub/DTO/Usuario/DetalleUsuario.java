package com.chub.DTO.Usuario;

import com.chub.persistence.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleUsuario(
        @NotNull(message = "¡El ID del usuario es obligatorio!")
        Long id,
        @NotBlank(message = "¡El nombre del usuario es obligatorio y no puede estar vacío!")
        String nombre,
        @NotBlank(message = "¡El correo electrónico es obligatorio y no puede estar vacío!")
        @Email(message = "¡Debe proporcionar una dirección de correo electrónico válida!")
        String correoElectronico) {

    public DetalleUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getCorreoElectronico());
    }
}
