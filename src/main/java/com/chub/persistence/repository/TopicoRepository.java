package com.chub.persistence.repository;

import com.chub.persistence.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TopicoRepository extends CrudRepository<Topico, Long> {
    // Método para encontrar todos los tópicos con estado verdadero (activo) y paginarlos
    Page<Topico> findByEstadoTrue(Pageable pageable);
}
