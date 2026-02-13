package dev.nicolas.cadastrodeusuarios.Tarefas;

import dev.nicolas.cadastrodeusuarios.Tarefas.dto.TarefaDTO;
import dev.nicolas.cadastrodeusuarios.Tarefas.service.TarefaService;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TarefaControllerUI {

    private final TarefaService tarefaService;

    public TarefaControllerUI(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping("/tarefas")
    public String listarTarefas(Model model) {
        model.addAttribute("tarefas", new java.util.ArrayList<>());
        
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            
            if (authentication != null && authentication.isAuthenticated() && 
                !"anonymousUser".equals(authentication.getPrincipal())) {
                
                Object principal = authentication.getPrincipal();
                if (principal instanceof UsuarioModel) {
                    UsuarioModel usuario = (UsuarioModel) principal;
                    List<TarefaDTO> tarefas = tarefaService.listarTarefasPorUsuario(usuario.getId());
                    model.addAttribute("tarefas", tarefas);
                    model.addAttribute("usuario", usuario);
                } else {
                    // Para debug - mostrar o tipo do principal
                    model.addAttribute("erro", "Tipo de principal: " + principal.getClass().getName());
                }
            } else {
                // Usuário não autenticado
                model.addAttribute("erro", "Usuário não autenticado");
            }
        } catch (Exception e) {
            model.addAttribute("erro", "Erro ao carregar tarefas: " + e.getMessage());
        }
        
        return "tarefas/listarTarefas";
    }

    @GetMapping("/tarefas/nova")
    public String mostrarFormularioNovaTarefa(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UsuarioModel) {
            
            UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
            model.addAttribute("usuario", usuario);
            model.addAttribute("tarefa", new TarefaDTO());
        }
        
        return "tarefas/novaTarefa";
    }

    @PostMapping("/tarefas")
    public String criarTarefa(@ModelAttribute TarefaDTO tarefaDTO, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UsuarioModel) {
            
            UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
            tarefaDTO.setUsuarioId(usuario.getId());
            
            try {
                tarefaService.adicionarTarefa(tarefaDTO);
                redirectAttributes.addFlashAttribute("mensagem", "Tarefa criada com sucesso!");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("erro", "Erro ao criar tarefa: " + e.getMessage());
            }
        }
        
        return "redirect:/tarefas";
    }

    @GetMapping("/tarefas/editar/{id}")
    public String mostrarFormularioEditarTarefa(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UsuarioModel) {
            
            UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
            TarefaDTO tarefa = tarefaService.listarTarefaPorID(id);
            
            // Verificar se a tarefa pertence ao usuário logado
            if (tarefa != null && tarefa.getUsuarioId().equals(usuario.getId())) {
                model.addAttribute("tarefa", tarefa);
                model.addAttribute("usuario", usuario);
                return "tarefas/editarTarefa";
            } else {
                redirectAttributes.addFlashAttribute("erro", "Você não tem permissão para editar esta tarefa.");
            }
        }
        
        return "redirect:/tarefas";
    }

    @PostMapping("/tarefas/editar/{id}")
    public String atualizarTarefa(@PathVariable Long id, @ModelAttribute TarefaDTO tarefaDTO, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UsuarioModel) {
            
            UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
            TarefaDTO tarefaExistente = tarefaService.listarTarefaPorID(id);
            
            // Verificar se a tarefa pertence ao usuário logado
            if (tarefaExistente != null && tarefaExistente.getUsuarioId().equals(usuario.getId())) {
                tarefaDTO.setId(id);
                tarefaDTO.setUsuarioId(usuario.getId());
                
                try {
                    tarefaService.alterarTarefa(id, tarefaDTO);
                    redirectAttributes.addFlashAttribute("mensagem", "Tarefa atualizada com sucesso!");
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("erro", "Erro ao atualizar tarefa: " + e.getMessage());
                }
            } else {
                redirectAttributes.addFlashAttribute("erro", "Você não tem permissão para editar esta tarefa.");
            }
        }
        
        return "redirect:/tarefas";
    }

    @PostMapping("/tarefas/concluir/{id}")
    public String concluirTarefa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UsuarioModel) {
            
            UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
            TarefaDTO tarefa = tarefaService.listarTarefaPorID(id);
            
            // Verificar se a tarefa pertence ao usuário logado
            if (tarefa != null && tarefa.getUsuarioId().equals(usuario.getId())) {
                tarefa.setStatus("CONCLUIDA");
                tarefaService.alterarTarefa(id, tarefa);
                redirectAttributes.addFlashAttribute("mensagem", "Tarefa concluída com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Você não tem permissão para concluir esta tarefa.");
            }
        }
        
        return "redirect:/tarefas";
    }

    @PostMapping("/tarefas/deletar/{id}")
    public String deletarTarefa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated() && 
            authentication.getPrincipal() instanceof UsuarioModel) {
            
            UsuarioModel usuario = (UsuarioModel) authentication.getPrincipal();
            TarefaDTO tarefa = tarefaService.listarTarefaPorID(id);
            
            // Verificar se a tarefa pertence ao usuário logado
            if (tarefa != null && tarefa.getUsuarioId().equals(usuario.getId())) {
                tarefaService.deletarTarefaPorId(id);
                redirectAttributes.addFlashAttribute("mensagem", "Tarefa deletada com sucesso!");
            } else {
                redirectAttributes.addFlashAttribute("erro", "Você não tem permissão para deletar esta tarefa.");
            }
        }
        
        return "redirect:/tarefas";
    }
}
