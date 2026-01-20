package dev.nicolas.cadastrodeusuarios.Tarefas.controller;

import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private TarefaService tarefaService;

    // Adicionar Usuário (create)
    @PostMapping("/criar")
    public TarefaModel adicionarTarefa(TarefaModel tarefaModel) {
        return tarefaService.adicionarTarefa(tarefaModel);
    }

    // Listar Todos os Usuários (read)
    @GetMapping("/listar")
    public List<TarefaModel> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    // Procurar Usuario por ID (read)
    @GetMapping("/listar/{id}")
    public TarefaModel listarTarefaPorID(@PathVariable Long id) {
        return tarefaService.listarTarefaPorID(id);
    }
    // Alterar Usuário (update)
    @PutMapping("/alterar/{id}")
    public TarefaModel alterarTarefa(@PathVariable Long id, @RequestBody TarefaModel tarefaModel) {
        return tarefaService.alterarTarefa(id, tarefaModel);
    }

    // Deletar Usuário (delete)
    @DeleteMapping("/deletar/{id}")
    public void deletarTarefaPorID(@PathVariable Long id) {
        tarefaService.deletarTarefaPorId(id);
    }
}
