package dev.nicolas.cadastrodeusuarios.Tarefas;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
