package dev.nicolas.cadastrodeusuarios.Tarefas.Controller;

import org.springframework.web.bind.annotation.RestController;
import dev.nicolas.cadastrodeusuarios.Tarefas.Model.TarefaModel;
import dev.nicolas.cadastrodeusuarios.Tarefas.Service.TarefaService;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping("/tarefas")

public class TarefaController {
    private TarefaService tarefaService;

    public TarefaController (TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }
    
    @PostMapping
    List<TarefaModel> criarTarefa(@RequestBody TarefaModel tarefaModel) {
        return tarefaService.criarTarefa(tarefaModel);
    }


    @GetMapping
    List<TarefaModel> listarTarefa() {
        return tarefaService.listarTarefas();
    }
    
    @PutMapping
    List<TarefaModel> atualizarTarefa(TarefaModel tarefaModel) {
        return atualizarTarefa(tarefaModel);
    }
    
    @DeleteMapping("{id}")
    List<TarefaModel> deletarTarefa(@PathVariable("id") Long id) {
        return deletarTarefa(id);
    }


    
}
