package com.chub.controller;

import com.chub.DTO.curso.DetalleCurso;
import com.chub.DTO.curso.RegistrarCurso;
import com.chub.persistence.model.Curso;
import com.chub.service.interfaces.ICursoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);
    private final ICursoService academiaService;

    @Autowired
    public CursoController(ICursoService academiaService) {
        this.academiaService = academiaService;
    }

    @PostMapping
    public ResponseEntity<DetalleCurso> agregarCurso(@RequestBody @Valid RegistrarCurso cursoNuevoDTO,
                                                     UriComponentsBuilder uriBuilder) {
        Curso curso = academiaService.cambiarRegistroDTO(cursoNuevoDTO);
        academiaService.save(curso);
        DetalleCurso cursoDetalleDTO = new DetalleCurso(curso);
        URI url = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(cursoDetalleDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleCurso> obtenerCurso(@PathVariable Long idCurso) {
        Curso curso = academiaService.findById(idCurso);
        DetalleCurso cursoDetalleDTO = new DetalleCurso(curso);
        return ResponseEntity.ok(cursoDetalleDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DetalleCurso>> listarTodosLosCursos(@PageableDefault(size = 5) Pageable paginacion) {
        return ResponseEntity.ok(academiaService.findByEstadoTrue(paginacion).map(DetalleCurso::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalleCurso> modificarCurso(@RequestBody @Valid DetalleCurso cursoModificadoDTO) {
        Curso curso = academiaService.cambiarDetalleDTO(cursoModificadoDTO);
        academiaService.update(curso);
        return ResponseEntity.ok(new DetalleCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> borrarCurso(@PathVariable Long idCurso) {
        academiaService.cambiarEstado(idCurso);
        return ResponseEntity.noContent().build();
    }
}
