package dev.nicolas.cadastrodeusuarios.Usuario.controller;

import dev.nicolas.cadastrodeusuarios.Usuario.dto.UsuarioDTO;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas-vindas", description = "Essa rota é uma mensagem de boas-vindas.")
    public String boasVindas() {
        return "Olá mundo!";
    }


    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Adicionar Usuário (create)
    @PostMapping("/adicionar")
    public ResponseEntity<String> adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario =  usuarioService.adicionarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Usuário " + usuario.getNome() + " adicionado com sucesso | ID: " + usuario.getId()
        );
    }

    // Procurar Usuario por ID (read)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarUsuarioPorID(@PathVariable long id) {
        UsuarioDTO usuarioDTO = usuarioService.listarUsuarioPorID(id);
        if(usuarioDTO != null) {
            usuarioService.listarUsuarioPorID(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(usuarioDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário de ID " + id + "não foi encontrado.");
        }


    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    // Alterar Usuário (update)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.alterarUsuario(id, usuarioDTO);
        return ResponseEntity.status(HttpStatus.FOUND).body(
                "Usuário " + usuario.getNome() + " alterado com sucesso | ID: " + usuario.getId()
        );
    }

    // Deletar Usuário (delete)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> removerUsuario(@PathVariable Long id) {
        if(usuarioService.listarUsuarioPorID(id) != null) {
            usuarioService.deletarUsuarioPorId(id);
            return ResponseEntity.ok("Usuário de ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário de ID " + id + "não foi encontrado.");
        }
    }


}
