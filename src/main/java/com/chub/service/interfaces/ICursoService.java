package com.chub.service.interfaces;

import com.chub.DTO.curso.DetalleCurso;
import com.chub.DTO.curso.RegistrarCurso;
import com.chub.persistence.model.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICursoService {

    void save(Curso curso);
    Curso findById(Long id);
    List<Curso> findAll();
    Page<Curso> findByEstadoTrue(Pageable pageable);
    void cambiarEstado(Long id);
    void update(Curso curso);
    Curso cambiarRegistroDTO(RegistrarCurso cursoDTO);

    Curso cambiarDetalleDTO(DetalleCurso detalleCursoDTO);
}
