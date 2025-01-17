package com.chub.DTO.topico;

import com.chub.DTO.Usuario.DetalleUsuario;
import com.chub.DTO.curso.DetalleCurso;
import com.chub.persistence.model.Topico;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DetalleTopico(
        @NotNull(message = "¡El ID del tópico es obligatorio!")
        Long id,
        @NotBlank(message = "¡El título del tópico es obligatorio y no puede estar vacío!")
        String titulo,
        @NotBlank(message = "¡El mensaje del tópico es obligatorio y no puede estar vacío!")
        String mensaje,
        @AssertTrue(message = "¡El estado del tópico debe ser verdadero!")
        boolean estado,
        @FutureOrPresent(message = "¡La fecha de creación debe ser en el presente o futuro!")
        LocalDateTime fechaCreacion,
        @NotBlank(message = "¡El autor del tópico es obligatorio y no puede estar vacío!")
        @Valid
        DetalleUsuario autor,
        @NotBlank(message = "¡El curso del tópico es obligatorio y no puede estar vacío!")
        @Valid
        DetalleCurso curso) {

        public DetalleTopico(Topico topico) {
                this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                        topico.isEstado(), topico.getFechaCreacion(),
                        new DetalleUsuario(topico.getAutor()),
                        new DetalleCurso(topico.getCurso()));
        }
}
