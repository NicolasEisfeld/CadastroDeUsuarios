package dev.nicolas.cadastrodeusuarios.Usuario.controller;

import dev.nicolas.cadastrodeusuarios.Usuario.dto.UsuarioDTO;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Olá mundo!";
    }


    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Adicionar Usuário (create)
    @PostMapping("/adicionar")
    public UsuarioDTO adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.adicionarUsuario(usuarioDTO);
    }

    // Procurar Usuario por ID (read)
    @GetMapping("/listar/{id}")
    public UsuarioDTO listarUsuarioPorID(@PathVariable long id) {
        return usuarioService.listarUsuarioPorID(id);
    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listar")
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Alterar Usuário (update)
    @PutMapping("/alterar/{id}")
    public UsuarioDTO alterarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.alterarUsuario(id, usuarioDTO);
    }

    // Deletar Usuário (delete)
    @DeleteMapping("/deletar/{id}")
    public void removerUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuarioPorId(id);
    }


}
