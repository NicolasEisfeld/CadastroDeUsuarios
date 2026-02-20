package dev.nicolas.cadastrodeusuarios.Usuario.controller;

import dev.nicolas.cadastrodeusuarios.Usuario.dto.UsuarioDTO;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }


    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas-vindas", description = "Essa rota é uma mensagem de boas-vindas.")
    public String boasVindas() {
        return "Olá mundo!";
    }

    // Adicionar Usuário (create)
    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar Usuário", description = "Essa rota cadastra um novo usuário e insere no Banco de Dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário adicionado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Requisição inválida")
    })
    public ResponseEntity<String> adicionarUsuario(
            @Parameter(description = "O Usuário manda os dados em Json no corpo da requisição")
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario =  usuarioService.adicionarUsuario(usuarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Usuário " + usuario.getNome() + " adicionado com sucesso | ID: " + usuario.getId()
        );
    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listar")
    @Operation(summary = "Listar Usuários", description = "Essa rota lista todos os usuários que estão cadastrados no Banco de Dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuários encontrados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados com sucesso")
    })
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarUsuarios();
        return ResponseEntity.ok(usuarios);
    }


    // Procurar Usuario por ID (read)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar Usuário por ID", description = "Essa rota permite visualizar um Usuário a partir da busca por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<?> listarUsuarioPorID(
            @Parameter(description = "O Usuário manda o ID no caminho da requisição para listar")
            @PathVariable long id) {
        UsuarioDTO usuarioDTO = usuarioService.listarUsuarioPorID(id);
        if(usuarioDTO != null) {
            usuarioService.listarUsuarioPorID(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(usuarioDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário de ID " + id + "não foi encontrado.");
        }


    }


    // Alterar Usuário (update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Alterar Usuário", description = "Essa rota altera um usuário cadastrado no Banco de Dados através do ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao tentar alterar")
    })
    public ResponseEntity<String> alterarUsuario(
            @Parameter(description = "O Usuário manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "O Usuário manda os novos dados em Json no corpo da requisição")
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuario = usuarioService.alterarUsuario(id, usuarioDTO);
        return ResponseEntity.status(HttpStatus.FOUND).body(
                "Usuário " + usuario.getNome() + " alterado com sucesso | ID: " + usuario.getId()
        );
    }

    // Deletar Usuário (delete)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar Usuário", description = "Essa rota deleta um usuário cadastrado no Banco de Dados através do ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao tentar deletar")
    })
    public ResponseEntity<String> removerUsuario(
            @Parameter(description = "O Usuário manda o ID no caminho da requisição para deletar")
            @PathVariable Long id) {
        if(usuarioService.listarUsuarioPorID(id) != null) {
            usuarioService.deletarUsuarioPorId(id);
            return ResponseEntity.ok("Usuário de ID " + id + " deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário de ID " + id + "não foi encontrado.");
        }
    }


}
