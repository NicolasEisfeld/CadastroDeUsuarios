package dev.nicolas.cadastrodeusuarios.Tarefas.controller;

import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private TarefaService tarefaService;

    @PostMapping("/criar")
    public TarefaModel adicionarTarefa(TarefaModel tarefaModel) {
        return tarefaService.adicionarTarefa(tarefaModel);
    }

    @GetMapping("/listar")
    public List<TarefaModel> listarTarefas() {
        return tarefaService.listarTarefas();
    }

    @GetMapping("/listar/{id}")
    public TarefaModel listarTarefaPorID(@PathVariable Long id) {
        return tarefaService.listarTarefaPorID(id);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarTarefaPorID(@PathVariable Long id) {
        tarefaService.deletarTarefaPorId(id);
    }
}
