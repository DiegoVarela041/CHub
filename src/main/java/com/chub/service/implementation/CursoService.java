package com.chub.service.implementation;

import com.chub.DTO.curso.DetalleCurso;
import com.chub.DTO.curso.RegistrarCurso;
import com.chub.persistence.model.Curso;
import com.chub.persistence.repository.CursoRepository;
import com.chub.service.interfaces.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService implements ICursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public void save(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public Curso findById(Long id) {
        Optional<Curso> cursoOptional = cursoRepository.findById(id);
        if (cursoOptional.isPresent()) {
            return cursoOptional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso no encontrado");
    }

    @Override
    public List<Curso> findAll() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    public Page<Curso> findByEstadoTrue(Pageable pageable) {
        return cursoRepository.findByEstadoTrue(pageable);
    }

    @Override
    public void cambiarEstado(Long id) {
        Curso curso = findById(id);
        curso.setEstado(false);
    }

    @Override
    public void update(Curso curso) {
        cursoRepository.save(curso);
    }

    @Override
    public Curso cambiarRegistroDTO(RegistrarCurso cursoDTO) {
        // Método para cambiar de RegistrarCursoDTO a Curso
        return new Curso(null, cursoDTO.nombre(), cursoDTO.categoria(), true);
    }

    @Override
    public Curso cambiarDetalleDTO(DetalleCurso detalleCursoDTO) {
        Curso curso = findById(detalleCursoDTO.id());
        curso.actualizarDatos(detalleCursoDTO);
        return new Curso(detalleCursoDTO.id(), detalleCursoDTO.nombre(), detalleCursoDTO.categoria(), detalleCursoDTO.estado());
    }
}
