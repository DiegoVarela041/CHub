package com.chub.service.implementation;

import com.chub.DTO.respuesta.RegistroRespuesta;
import com.chub.persistence.model.Respuesta;
import com.chub.persistence.model.Topico;
import com.chub.persistence.model.Usuario;
import com.chub.persistence.repository.RespuestaRepository;
import com.chub.service.interfaces.IRespuestaservice;
import com.chub.service.interfaces.ITopicoService;
import com.chub.service.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RespuestaService implements IRespuestaservice {

    private final RespuestaRepository respuestaRepository;
    private final IUsuarioService usuarioService;
    private final ITopicoService topicoService;

    @Autowired
    public RespuestaService(RespuestaRepository respuestaRepository, IUsuarioService usuarioService, ITopicoService topicoService) {
        this.respuestaRepository = respuestaRepository;
        this.usuarioService = usuarioService;
        this.topicoService = topicoService;
    }

    @Override
    public void save(Respuesta respuesta) {
        respuestaRepository.save(respuesta);
    }

    @Override
    public void update(Respuesta respuesta) {
        respuestaRepository.save(respuesta);
    }

    @Override
    public Respuesta findById(Long id) {
        return respuestaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Respuesta no encontrada"));
    }

    @Override
    public List<Respuesta> findAll() {
        return (List<Respuesta>) respuestaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        respuestaRepository.deleteById(id);
    }

    @Override
    public Respuesta cambiarRegistroRespuestaDTO(RegistroRespuesta registroRespuestaDTO) {
        Usuario autor = usuarioService.findById(registroRespuestaDTO.autor());
        Topico topico = topicoService.findById(registroRespuestaDTO.topico());
        return new Respuesta(null, registroRespuestaDTO.mensaje(), LocalDateTime.now(), registroRespuestaDTO.solucion(), autor, topico);
    }

    @Override
    public boolean estaPresente(Long id) {
        return respuestaRepository.existsById(id);
    }

    @Override
    public Respuesta cambiarRegistroActualizarRespuestaDTO(Long id, RegistroRespuesta registroRespuestaDTO) {
        Respuesta respuesta = findById(id);
        respuesta.setMensaje(registroRespuestaDTO.mensaje());
        respuesta.setSolucion(registroRespuestaDTO.solucion());
        respuesta.setAutor(usuarioService.findById(registroRespuestaDTO.autor()));
        respuesta.setTopico(topicoService.findById(registroRespuestaDTO.topico()));
        return respuesta;
    }

    @Override
    public List<Respuesta> buscarRespuestasPorTopico(Topico topico) {
        return respuestaRepository.findByTopico(topico);
    }
}
