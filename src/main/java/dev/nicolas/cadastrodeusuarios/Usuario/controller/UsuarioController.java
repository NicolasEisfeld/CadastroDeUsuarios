package dev.nicolas.cadastrodeusuarios.Usuario.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UsuarioController {
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Olá mundo!";
    }

    // Adicionar Usuário (create)
    @PostMapping("/adicionar")
    public String adicionarUsuario() {
        return "Usuário adicionado";
    }

    // Procurar Usuario por ID (read)
    @GetMapping("/procurar")
    public String procurarUsuario() {
        return "Usuário encontrado";
    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listartodos")
    public String listarUsuarios() {
        return "Lista de Usuários";
    }

    // Alterar Usuário (update)
    @PutMapping("/alterar")
    public String alterarUsuario() {
        return "Usuario Alterado";
    }

    // Deletar Usuário (delete)
    @DeleteMapping
    public String removerUsuario() {
        return "Usuario Removido";
    }


}
