package dev.nicolas.cadastrodeusuarios.Usuario.repository;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
}
