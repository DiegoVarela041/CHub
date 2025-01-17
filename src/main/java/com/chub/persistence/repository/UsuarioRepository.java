package com.chub.persistence.repository;

import com.chub.persistence.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    // Método para encontrar un usuario por su correo electrónico
    UserDetails findByCorreoElectronico(String correoElectronico);
}
