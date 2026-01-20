package dev.nicolas.cadastrodeusuarios.Usuario.service;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Adicionar Usuário
    public UsuarioModel adicionarUsuario(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }

    // Listar Todos os Usuários
    public List<UsuarioModel> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Listar Usuário por ID
    public UsuarioModel listarUsuarioPorID(Long id) {
        Optional <UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        return usuarioModel.orElse(null);
    }

    // Alterar Usuário
    public UsuarioModel alterarUsuario(Long id, UsuarioModel usuarioModel) {
        if(usuarioRepository.existsById(id)) {
            usuarioModel.setId(id);
            return usuarioRepository.save(usuarioModel);
        } else {
            return null;
        }
    }

    // Deletar Usuário por ID
    public void deletarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
        
    }


}
