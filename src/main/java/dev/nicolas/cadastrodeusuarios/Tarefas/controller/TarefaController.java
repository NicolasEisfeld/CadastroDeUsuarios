package dev.nicolas.cadastrodeusuarios.Tarefas.controller;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaService  tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // Adicionar Tarefa (create)
    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar Tarefa", description = "Essa rota cadastra uma nova tarefa e insere no Banco de Dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa adicionada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Requisição inválida")
    })
    public ResponseEntity<String> adicionarTarefa(
            @Parameter(description = "O Usuário manda os dados em Json no corpo da requisição")
            @RequestBody TarefaDTO tarefaDTO) {
        tarefaService.adicionarTarefa(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Tarefa criada com sucesso!"
        );
    }

    // Listar Todos os Tarefas (read)
    @GetMapping("/listar")
    @Operation(summary = "Listar Tarefas", description = "Essa rota lista todas as tarefas que estão cadastradas no Banco de Dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefas encontradas com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefas não encontradas")
    })
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> tarefas = tarefaService.listarTarefas();
        return(ResponseEntity.ok(tarefas));
    }

    // Procurar Tarefa por ID (read)
    @GetMapping("/listar/{id}")
    @Operation(summary = "Listar Tarefa por ID", description = "Essa rota permite visualizar uma tarefa a partir da busca por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    public ResponseEntity<?> listarTarefaPorID(
            @Parameter(description = "O Usuário manda o ID no caminho da requisição para listar")
            @PathVariable Long id) {
        TarefaDTO tarefaDTO = tarefaService.listarTarefaPorID(id);
        if(tarefaDTO != null) {
            return ResponseEntity.ok("Tarefa Encontrada!\n" + tarefaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa de ID " + id + " não foi encontrada.");
        }
    }

    // Alterar Tarefa (update)
    @PutMapping("/alterar/{id}")
    @Operation(summary = "Alterar Tarefa", description = "Essa rota altera uma tarefa cadastrada no Banco de Dados através do ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao tentar alterar")
    })
    public ResponseEntity<String> alterarTarefa(
            @Parameter(description = "O Usuário manda o ID no caminho da requisição")
            @PathVariable Long id,
            @Parameter(description = "O Usuário manda os novos dados em Json no corpo da requisição")
            @RequestBody TarefaDTO tarefaDTO) {

        if(tarefaService.listarTarefaPorID(id) != null) {
            tarefaService.alterarTarefa(id, tarefaDTO);
            return ResponseEntity.ok("Tarefa alterada com sucesso!");
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa de ID " + id + " não foi encontrada.");
        }

    }

    // Deletar Tarefa (delete)
    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deletar Tarefa", description = "Essa rota deleta uma tarefa cadastrada no Banco de Dados através do ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro ao tentar deletar")
    })
    public ResponseEntity<String> deletarTarefaPorID(
            @Parameter(description = "O Usuário manda o ID no caminho da requisição para deletar")
            @PathVariable Long id) {
        if(tarefaService.listarTarefaPorID(id) != null) {
            tarefaService.deletarTarefaPorId(id);
            return ResponseEntity.ok("Tarefa de ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa de ID " + id + " não foi encontrada.");
        }

    }
}
