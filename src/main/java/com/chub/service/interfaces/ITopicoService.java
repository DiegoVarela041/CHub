package com.chub.service.interfaces;

import com.chub.DTO.topico.RegistrarTopico;
import com.chub.persistence.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITopicoService {

    void save(Topico topico); // Guardar un nuevo tópico
    List<Topico> findAll(); // Buscar todos los tópicos
    Page<Topico> findByEstadoTrue(Pageable pageable); // Buscar todos los tópicos con estado verdadero (activo) y paginarlos
    Topico findById(Long id); // Buscar un tópico por su ID
    void cambiarEstado(Long id); // Cambiar el estado de un tópico
    void update(Topico topico); // Actualizar un tópico existente
    Topico cambiarRegistroTopicoDTO(RegistrarTopico registrarTopicoDTO); // Convertir DTO a tópico
    Topico cambiarRegistroActualizarTopicoDTO(Long id, RegistrarTopico registrarTopicoDTO); // Actualizar un tópico a partir de un DTO
    boolean estaPresente(Long id); // Verificar si un tópico existe por su ID
}
