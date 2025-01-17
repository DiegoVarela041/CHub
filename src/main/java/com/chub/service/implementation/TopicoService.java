package com.chub.service.implementation;

import com.chub.DTO.topico.RegistrarTopico;
import com.chub.persistence.model.Curso;
import com.chub.persistence.model.Topico;
import com.chub.persistence.model.Usuario;
import com.chub.persistence.repository.TopicoRepository;
import com.chub.service.interfaces.ICursoService;
import com.chub.service.interfaces.ITopicoService;
import com.chub.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService implements ITopicoService {

    private final TopicoRepository topicoRepository;
    private final ICursoService cursoService;
    private final IUsuarioService usuarioService;

    @Autowired
    public TopicoService(TopicoRepository topicoRepository, ICursoService cursoService, IUsuarioService usuarioService) {
        this.topicoRepository = topicoRepository;
        this.cursoService = cursoService;
        this.usuarioService = usuarioService;
    }

    @Override
    public void save(Topico topico) {
        topicoRepository.save(topico);
    }

    @Override
    public List<Topico> findAll() {
        return (List<Topico>) topicoRepository.findAll();
    }

    @Override
    public Page<Topico> findByEstadoTrue(Pageable pageable) {
        return topicoRepository.findByEstadoTrue(pageable);
    }

    @Override
    public Topico findById(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));
    }

    @Override
    public void cambiarEstado(Long id) {
        Topico topico = findById(id);
        topico.setEstado(false);
        topicoRepository.save(topico);
    }

    @Override
    public void update(Topico topico) {
        topicoRepository.save(topico);
    }

    @Override
    public Topico cambiarRegistroTopicoDTO(RegistrarTopico registrarTopicoDTO) {
        Usuario usuario = usuarioService.findById(registrarTopicoDTO.autor());
        Curso curso = cursoService.findById(registrarTopicoDTO.curso());
        return new Topico(null, registrarTopicoDTO.titulo(), registrarTopicoDTO.mensaje(), LocalDateTime.now(), true, usuario, curso);
    }

    @Override
    public Topico cambiarRegistroActualizarTopicoDTO(Long id, RegistrarTopico registrarTopicoDTO) {
        Topico topico = findById(id);
        topico.setTitulo(registrarTopicoDTO.titulo());
        topico.setMensaje(registrarTopicoDTO.mensaje());
        topico.setAutor(usuarioService.findById(registrarTopicoDTO.autor()));
        topico.setCurso(cursoService.findById(registrarTopicoDTO.curso()));
        return topico;
    }

    @Override
    public boolean estaPresente(Long id) {
        return topicoRepository.existsById(id);
    }
}
