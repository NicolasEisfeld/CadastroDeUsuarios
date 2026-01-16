package dev.nicolas.cadastrodeusuarios.Usuario.service;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar Todos os Usuários
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Listar Usuário por ID
    public UsuarioModel listarUsuarioPorID(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

}
