package dev.nicolas.cadastrodeusuarios.Usuarios.Service;

import dev.nicolas.cadastrodeusuarios.Usuarios.Dto.UsuarioRequest;
import dev.nicolas.cadastrodeusuarios.Usuarios.Dto.UsuarioResponse;
import dev.nicolas.cadastrodeusuarios.Usuarios.Model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuarios.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Criar novo usuário
    public UsuarioResponse criarUsuario(UsuarioRequest request) {
        // Verificar se email já existe
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + request.getEmail());
        }

        UsuarioModel usuario = new UsuarioModel();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha()); // TODO: Implementar criptografia

        UsuarioModel usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                LocalDateTime.now());
    }

    // Buscar todos os usuários
    @Transactional(readOnly = true)
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuario -> new UsuarioResponse(
                        usuario.getId(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        LocalDateTime.now() // TODO: Adicionar campo dataCriacao no modelo
                ))
                .collect(Collectors.toList());
    }

    // Buscar usuário por ID
    @Transactional(readOnly = true)
    public UsuarioResponse buscarUsuarioPorId(Long id) {
        Optional<UsuarioModel> usuario = usuarioRepository.findById(id);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }

        UsuarioModel usuarioEncontrado = usuario.get();
        return new UsuarioResponse(
                usuarioEncontrado.getId(),
                usuarioEncontrado.getNome(),
                usuarioEncontrado.getEmail(),
                LocalDateTime.now() // TODO: Adicionar campo dataCriacao no modelo
        );
    }

    // Atualizar usuário
    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request) {
        Optional<UsuarioModel> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }

        UsuarioModel usuario = usuarioOpt.get();

        // Verificar se email já existe em outro usuário
        if (!usuario.getEmail().equals(request.getEmail()) &&
                usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email já cadastrado: " + request.getEmail());
        }

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha()); // TODO: Implementar criptografia

        UsuarioModel usuarioAtualizado = usuarioRepository.save(usuario);

        return new UsuarioResponse(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getNome(),
                usuarioAtualizado.getEmail(),
                LocalDateTime.now());
    }

    // Deletar usuário
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Buscar usuário por email
    @Transactional(readOnly = true)
    public UsuarioResponse buscarUsuarioPorEmail(String email) {
        Optional<UsuarioModel> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado com email: " + email);
        }

        UsuarioModel usuarioEncontrado = usuario.get();
        return new UsuarioResponse(
                usuarioEncontrado.getId(),
                usuarioEncontrado.getNome(),
                usuarioEncontrado.getEmail(),
                LocalDateTime.now());
    }
}
