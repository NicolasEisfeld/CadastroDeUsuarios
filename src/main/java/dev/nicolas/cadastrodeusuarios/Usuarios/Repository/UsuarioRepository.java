package dev.nicolas.cadastrodeusuarios.Usuarios.Repository;

import dev.nicolas.cadastrodeusuarios.Usuarios.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

    // Buscar usuário por email
    Optional<UsuarioModel> findByEmail(String email);

    // Verificar se email já existe
    boolean existsByEmail(String email);

    // Buscar usuário por nome (para busca)
    Optional<UsuarioModel> findByNome(String nome);
}
