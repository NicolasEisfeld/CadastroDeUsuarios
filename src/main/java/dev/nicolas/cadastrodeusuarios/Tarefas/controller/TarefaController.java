package dev.nicolas.cadastrodeusuarios.Tarefas.controller;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
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

    // Adicionar Usuário (create)
    @PostMapping("/criar")
    public ResponseEntity<String> adicionarTarefa(TarefaDTO tarefaDTO) {
        tarefaService.adicionarTarefa(tarefaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                "Tarefa criada com sucesso!"
        );
    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listar")
    public ResponseEntity<List<TarefaDTO>> listarTarefas() {
        List<TarefaDTO> tarefas = tarefaService.listarTarefas();
        return(ResponseEntity.ok(tarefas));
    }

    // Procurar Usuario por ID (read)
    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarTarefaPorID(@PathVariable Long id) {
        TarefaDTO tarefaDTO = tarefaService.listarTarefaPorID(id);
        if(tarefaDTO != null) {
            return ResponseEntity.ok("Tarefa Encontrada!\n" + tarefaDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa de ID " + id + " não foi encontrada.");
        }
    }
    // Alterar Usuário (update)
    @PutMapping("/alterar/{id}")
    public ResponseEntity<String> alterarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO) {

        if(tarefaService.listarTarefaPorID(id) != null) {
            tarefaService.alterarTarefa(id, tarefaDTO);
            return ResponseEntity.ok("Tarefa alterada com sucesso!");
        } else {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa de ID " + id + " não foi encontrada.");
        }

    }

    // Deletar Usuário (delete)
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarTarefaPorID(@PathVariable Long id) {
        if(tarefaService.listarTarefaPorID(id) != null) {
            tarefaService.deletarTarefaPorId(id);
            return ResponseEntity.ok("Tarefa de ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tarefa de ID " + id + " não foi encontrada.");
        }

    }
}
