package com.chub.persistence.repository;

import com.chub.persistence.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    // Método para encontrar todos los cursos con estado verdadero (activo) y paginarlos
    Page<Curso> findByEstadoTrue(Pageable pageable);
}
