package com.chub.controller;

import com.chub.DTO.respuesta.DetalleRespuesta;
import com.chub.DTO.topico.DetalleTopicoConRespuesta;
import com.chub.DTO.topico.RegistrarTopico;
import com.chub.DTO.topico.DetalleTopico;
import com.chub.persistence.model.Topico;
import com.chub.service.interfaces.IRespuestaservice;
import com.chub.service.interfaces.ITopicoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final ITopicoService foroService;
    private final IRespuestaservice respuestaService;

    @Autowired
    public TopicoController(ITopicoService foroService, IRespuestaservice respuestaService) {
        this.foroService = foroService;
        this.respuestaService = respuestaService;
    }

    @PostMapping
    public ResponseEntity<DetalleTopico> crearTopico(@RequestBody @Valid RegistrarTopico nuevoTopicoDTO,
                                                     UriComponentsBuilder uriBuilder) {
        Topico nuevoTopico = foroService.cambiarRegistroTopicoDTO(nuevoTopicoDTO);
        foroService.save(nuevoTopico);
        DetalleTopico detalleTopicoDTO = new DetalleTopico(nuevoTopico);
        URI url = uriBuilder.path("/cursos/{id}").buildAndExpand(nuevoTopico.getId()).toUri();
        return ResponseEntity.created(url).body(detalleTopicoDTO);
    }

    @GetMapping
    public ResponseEntity<Page<DetalleTopico>> listarTopicos(@PageableDefault(size = 10, sort = "fechaCreacion",
            direction = Sort.Direction.ASC) Pageable paginacion) {
        return ResponseEntity.ok(foroService.findByEstadoTrue(paginacion).map(DetalleTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopico> obtenerTopico(@PathVariable Long idTopico) {
        return ResponseEntity.ok(new DetalleTopico(foroService.findById(idTopico)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalleTopico> actualizarTopico(@PathVariable Long idTopico, @RequestBody @Valid RegistrarTopico actualizacionTopicoDTO) {
        if (foroService.estaPresente(idTopico)) {
            Topico topicoActualizado = foroService.cambiarRegistroActualizarTopicoDTO(idTopico, actualizacionTopicoDTO);
            foroService.update(topicoActualizado);
            return ResponseEntity.ok(new DetalleTopico(topicoActualizado));
        }
        throw new EntityNotFoundException("El tópico con ID " + idTopico + " no fue encontrado. Por favor verifica y vuelve a intentar.");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long idTopico) {
        foroService.cambiarEstado(idTopico);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/respuestas")
    public ResponseEntity<DetalleTopicoConRespuesta> listarRespuestasPorTopico(@PathVariable Long idTopico) {
        Topico topico = foroService.findById(idTopico);
        List<DetalleRespuesta> respuestas = respuestaService.buscarRespuestasPorTopico(topico).stream().map(DetalleRespuesta::new).toList();
        return ResponseEntity.ok(new DetalleTopicoConRespuesta(topico, respuestas));
    }
}
