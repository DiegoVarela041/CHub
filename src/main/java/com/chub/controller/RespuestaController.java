package com.chub.controller;

import com.chub.DTO.respuesta.DetalleRespuesta;
import com.chub.DTO.respuesta.RegistroRespuesta;
import com.chub.persistence.model.Respuesta;
import com.chub.service.interfaces.IRespuestaservice;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    private final IRespuestaservice solucionService;

    @Autowired
    public RespuestaController(IRespuestaservice solucionService) {
        this.solucionService = solucionService;
    }

    @PostMapping
    public ResponseEntity<DetalleRespuesta> crearRespuesta(@RequestBody @Valid RegistroRespuesta nuevaRespuestaDTO,
                                                           UriComponentsBuilder uriBuilder) {
        Respuesta nuevaRespuesta = solucionService.cambiarRegistroRespuestaDTO(nuevaRespuestaDTO);
        solucionService.save(nuevaRespuesta);
        URI url = uriBuilder.path("/respuestas/{id}").build(nuevaRespuesta.getId());
        return ResponseEntity.created(url).body(new DetalleRespuesta(nuevaRespuesta));
    }

    @GetMapping
    public ResponseEntity<List<DetalleRespuesta>> obtenerRespuestas() {
        return ResponseEntity.ok(solucionService.findAll().stream().map(DetalleRespuesta::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalleRespuesta> actualizarRespuesta(@PathVariable Long id,
                                                                @RequestBody @Valid RegistroRespuesta actualizacionRespuestaDTO) {
        if (solucionService.estaPresente(id)) {
            Respuesta respuestaActualizada = solucionService.cambiarRegistroActualizarRespuestaDTO(id, actualizacionRespuestaDTO);
            solucionService.update(respuestaActualizada);
            return ResponseEntity.ok(new DetalleRespuesta(respuestaActualizada));
        }
        throw new EntityNotFoundException();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> borrarRespuesta(@PathVariable Long id) {
        solucionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
