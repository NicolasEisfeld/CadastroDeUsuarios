package dev.nicolas.cadastrodeusuarios.Tarefas;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
public class TarefaControllerUI {

    private final TarefaService tarefaService;

    public TarefaControllerUI(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefas")
    public String listarTarefas(Model model) {
        List<TarefaDTO> tarefas = tarefaService.listarTarefas();
        model.addAttribute("tarefas", tarefas);
        return "tarefas/listarTarefas";
    }

    @PutMapping
    public String alterarTarefaPorId(@PathVariable long id, Model model) {
        TarefaDTO tarefaDTO = tarefaService.listarTarefaPorID(id);
        model.addAttribute("tarefa", tarefaDTO);
        return "tarefas/alterarTarefa";
    }

    @DeleteMapping("/tarefas/deletar/{id}")
    public String deletarTarefaPorId(@PathVariable long id) {
        tarefaService.deletarTarefaPorId(id);
        return "tarefas/deletarTarefaSucesso";
    }


}
