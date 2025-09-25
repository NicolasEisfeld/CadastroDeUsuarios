package dev.nicolas.cadastrodeusuarios.Usuarios.Controller;

import dev.nicolas.cadastrodeusuarios.Usuarios.Dto.UsuarioRequest;
import dev.nicolas.cadastrodeusuarios.Usuarios.Dto.UsuarioResponse;
import dev.nicolas.cadastrodeusuarios.Usuarios.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Criar novo usuário
    @PostMapping
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse usuario = usuarioService.criarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Buscar usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse usuario = usuarioService.atualizarUsuario(id, request);
        return ResponseEntity.ok(usuario);
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar usuário por email
    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorEmail(@PathVariable String email) {
        UsuarioResponse usuario = usuarioService.buscarUsuarioPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    // Endpoint de teste
    @GetMapping("/hello")
    public String hello() {
        return "API de Cadastro de Usuários funcionando!";
    }
}
