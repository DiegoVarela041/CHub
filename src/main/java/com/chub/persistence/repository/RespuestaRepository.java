package com.chub.persistence.repository;

import com.chub.persistence.model.Respuesta;
import com.chub.persistence.model.Topico;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RespuestaRepository extends CrudRepository<Respuesta, Long> {
    // Método para encontrar todas las respuestas asociadas a un tópico específico
    List<Respuesta> findByTopico(Topico topico);
}
