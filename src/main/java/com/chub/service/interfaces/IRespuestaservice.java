package com.chub.service.interfaces;

import com.chub.DTO.respuesta.RegistroRespuesta;
import com.chub.persistence.model.Respuesta;
import com.chub.persistence.model.Topico;

import java.util.List;

public interface IRespuestaservice {

    void save(Respuesta respuesta); // Guardar una nueva respuesta
    void update(Respuesta respuesta); // Actualizar una respuesta existente
    Respuesta findById(Long id); // Buscar una respuesta por su ID
    List<Respuesta> findAll(); // Buscar todas las respuestas
    void deleteById(Long id); // Eliminar una respuesta por su ID
    Respuesta cambiarRegistroRespuestaDTO(RegistroRespuesta registroRespuestaDTO); // Convertir DTO a respuesta
    boolean estaPresente(Long id); // Verificar si una respuesta existe por su ID
    Respuesta cambiarRegistroActualizarRespuestaDTO(Long id, RegistroRespuesta registroRespuestaDTO); // Actualizar una respuesta a partir de un DTO
    List<Respuesta> buscarRespuestasPorTopico(Topico topico); // Buscar respuestas por tópico
}
