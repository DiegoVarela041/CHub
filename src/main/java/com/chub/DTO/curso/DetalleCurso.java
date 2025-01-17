package com.chub.DTO.curso;

import com.chub.persistence.model.Curso;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalleCurso(
        @NotNull
        Long id,
        @NotBlank(message = "El nombre del curso no puede estar vacío.")
        String nombre,
        @NotBlank(message = "La categoría del curso no puede estar vacía.")
        String categoria,
        @AssertTrue(message = "El estado del curso debe ser verdadero.")
        Boolean estado) {

    public DetalleCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCategoria(), curso.isEstado());
    }
}
