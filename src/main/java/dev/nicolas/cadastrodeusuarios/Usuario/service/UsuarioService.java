package dev.nicolas.cadastrodeusuarios.Usuario.service;

import dev.nicolas.cadastrodeusuarios.Usuario.dto.UsuarioDTO;
import dev.nicolas.cadastrodeusuarios.Usuario.mapper.UsuarioMapper;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    // Adicionar Usuário
    public UsuarioDTO adicionarUsuario(UsuarioDTO usuarioDTO) {
        UsuarioModel usuarioModel = usuarioMapper.map(usuarioDTO);
        usuarioModel = usuarioRepository.save(usuarioModel);
        return usuarioMapper.map(usuarioModel);
    }

    // Listar Todos os Usuários
    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioModel> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::map)
                .toList();
    }

    // Listar Usuário por ID
    public UsuarioDTO listarUsuarioPorID(Long id) {
        Optional <UsuarioModel> usuarioModel = usuarioRepository.findById(id);
        return usuarioModel.map(usuarioMapper::map).orElse(null);
    }

    // Alterar Usuário
    public UsuarioDTO alterarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional <UsuarioModel> usuarioExistente = usuarioRepository.findById(id);
        if(usuarioExistente.isPresent()) {
            UsuarioModel usuarioAtualizado = usuarioMapper.map(usuarioDTO);
            usuarioAtualizado.setId(id);
            usuarioRepository.save(usuarioAtualizado);
            return usuarioMapper.map(usuarioAtualizado);
        }
        return null;
    }

    // Deletar Usuário por ID
    public void deletarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
        
    }


}
