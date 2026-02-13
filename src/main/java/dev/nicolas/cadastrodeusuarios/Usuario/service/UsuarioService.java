package dev.nicolas.cadastrodeusuarios.Usuario.service;

import dev.nicolas.cadastrodeusuarios.Usuario.dto.UsuarioDTO;
import dev.nicolas.cadastrodeusuarios.Usuario.mapper.UsuarioMapper;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
        this.passwordEncoder = passwordEncoder;
    }

    // Adicionar Usuário
    public UsuarioDTO adicionarUsuario(UsuarioDTO usuarioDTO) {
        log.info("Adicionando usuário com email: {}", usuarioDTO.getEmail());
        log.info("Senha original (não criptografada): {}", usuarioDTO.getPassword());
        
        UsuarioModel usuarioModel = usuarioMapper.map(usuarioDTO);
        
        // Criptografar senha antes de salvar
        if (usuarioModel.getPassword() != null && !usuarioModel.getPassword().isEmpty()) {
            String senhaCriptografada = passwordEncoder.encode(usuarioModel.getPassword());
            usuarioModel.setPassword(senhaCriptografada);
            log.info("Senha criptografada: {}", senhaCriptografada);
        }
        
        // Garantir que o usuário tenha pelo menos a role USER
        if (usuarioModel.getRoles() == null || usuarioModel.getRoles().isEmpty()) {
            usuarioModel.setRoles(List.of(UsuarioModel.Role.USER));
        }
        
        usuarioModel = usuarioRepository.save(usuarioModel);
        log.info("Usuário salvo com ID: {}", usuarioModel.getId());
        
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
