package com.chub.service.interfaces;

import com.chub.DTO.Usuario.RegistrarUsuario;
import com.chub.persistence.model.Usuario;

import java.util.List;

public interface IUsuarioService {
    void save(Usuario usuario); // Guardar un nuevo usuario
    void update(Usuario usuario); // Actualizar un usuario existente
    Usuario cambiarRegistroUsuarioDTO(RegistrarUsuario usuarioDTO); // Convertir DTO a usuario
    Usuario findById(Long id); // Buscar un usuario por su ID
    List<Usuario> findAll(); // Buscar todos los usuarios

}
