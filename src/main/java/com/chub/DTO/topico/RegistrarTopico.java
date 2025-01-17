package com.chub.DTO.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarTopico(
        @NotBlank(message = "¡El título del tópico es obligatorio y no puede estar vacío!")
        String titulo,
        @NotBlank(message = "¡El mensaje del tópico es obligatorio y no puede estar vacío!")
        String mensaje,
        @NotNull(message = "¡El ID del autor es obligatorio!")
        long autor,
        @NotNull(message = "¡El ID del curso es obligatorio!")
        long curso) {
}
