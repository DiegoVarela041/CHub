package com.chub.DTO.respuesta;

import com.chub.DTO.Usuario.DetalleUsuario;
import com.chub.DTO.topico.DetalleTopico;
import com.chub.persistence.model.Respuesta;

import java.time.LocalDateTime;

public record DetalleRespuesta(
        Long id,
        String mensaje,
        String solucion,
        LocalDateTime fechaDeCreacion,
        DetalleUsuario autor,
        DetalleTopico topico) {

    public DetalleRespuesta(Respuesta respuesta) {
        this(respuesta.getId(), respuesta.getMensaje(), respuesta.getSolucion(), respuesta.getFechaCreacion(),
                new DetalleUsuario(respuesta.getAutor()), new DetalleTopico(respuesta.getTopico()));
    }
}
