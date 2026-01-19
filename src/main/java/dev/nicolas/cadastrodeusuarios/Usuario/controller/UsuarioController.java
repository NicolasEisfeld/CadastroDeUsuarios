package dev.nicolas.cadastrodeusuarios.Usuario.controller;

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
    public UsuarioModel adicionarUsuario(@RequestBody UsuarioModel usuarioModel) {
        return usuarioService.adicionarUsuario(usuarioModel);
    }

    // Procurar Usuario por ID (read)
    @GetMapping("/listar/{id}")
    public UsuarioModel listarUsuarioPorID(@PathVariable long id) {
        return usuarioService.listarUsuarioPorID(id);
    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listar")
    public List<UsuarioModel> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Alterar Usuário (update)
    @PutMapping("/alterar")
    public String alterarUsuario() {
        return "Usuario Alterado";
    }

    // Deletar Usuário (delete)
    @DeleteMapping("/deletar/{id}")
    public void removerUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuarioPorId(id);
    }


}
