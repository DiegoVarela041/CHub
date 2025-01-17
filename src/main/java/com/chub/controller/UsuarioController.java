package com.chub.controller;

import com.chub.DTO.Usuario.AutenticarUsuario;
import com.chub.DTO.Usuario.DetalleUsuario;
import com.chub.DTO.Usuario.RegistrarUsuario;
import com.chub.DTO.token.DatosJWTtoken;
import com.chub.persistence.model.Usuario;
import com.chub.service.implementation.TokenService;
import com.chub.service.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService servicioUsuarios;
    private final AuthenticationManager gestorAutenticacion;
    private final TokenService servicioToken;

    @Autowired
    public UsuarioController(IUsuarioService servicioUsuarios, AuthenticationManager gestorAutenticacion, TokenService servicioToken) {
        this.servicioUsuarios = servicioUsuarios;
        this.gestorAutenticacion = gestorAutenticacion;
        this.servicioToken = servicioToken;
    }

    @PostMapping("/login")
    public ResponseEntity<DatosJWTtoken> autenticarUsuario(@RequestBody @Valid AutenticarUsuario datosAutenticacion) {
        Authentication tokenAutenticacion = new UsernamePasswordAuthenticationToken(datosAutenticacion.correoElectronico(),
                datosAutenticacion.contrasenia());
        var usuarioAutenticado = gestorAutenticacion.authenticate(tokenAutenticacion);
        var JWTtoken = servicioToken.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    @PostMapping
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid RegistrarUsuario datosRegistro) {
        Usuario nuevoUsuario = servicioUsuarios.cambiarRegistroUsuarioDTO(datosRegistro);
        servicioUsuarios.save(nuevoUsuario);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<DetalleUsuario>> listarUsuarios() {
        return ResponseEntity.ok(servicioUsuarios.findAll().stream().map(DetalleUsuario::new).toList());
    }
}
