package com.chub.DTO.curso;

import jakarta.validation.constraints.NotBlank;

public record RegistrarCurso(
        @NotBlank(message = "¡El nombre del curso es obligatorio y no puede estar vacío!")
        String nombre,
        @NotBlank(message = "¡La categoría del curso es obligatoria y no puede estar vacía!")
        String categoria) {
}
