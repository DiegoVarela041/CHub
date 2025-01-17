package com.chub.DTO.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroRespuesta(
        @NotBlank(message = "¡El mensaje es obligatorio y no puede estar vacío!")
        String mensaje,
        @NotBlank(message = "¡La solución es obligatoria y no puede estar vacía!")
        String solucion,
        @NotNull(message = "¡El ID del autor es obligatorio!")
        long autor,
        @NotNull(message = "¡El ID del tópico es obligatorio!")
        long topico) {
}
