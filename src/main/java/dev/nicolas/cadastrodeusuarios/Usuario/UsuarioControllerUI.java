package dev.nicolas.cadastrodeusuarios.Usuario;

import dev.nicolas.cadastrodeusuarios.Usuario.dto.UsuarioDTO;
import dev.nicolas.cadastrodeusuarios.Usuario.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsuarioControllerUI {

    private final UsuarioService usuarioService;

    public UsuarioControllerUI(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                           @RequestParam(value = "logout", required = false) String logout,
                           Model model) {
        
        if (error != null) {
            model.addAttribute("error", "Credenciais inválidas. Tente novamente.");
        }
        
        if (logout != null) {
            model.addAttribute("message", "Você fez logout com sucesso.");
        }
        
        return "login/login";
    }

    @GetMapping("/cadastro")
    public String exibirFormularioCadastro(Model model) {
        model.addAttribute("usuario", new UsuarioDTO());
        return "cadastro/adicionarUsuario";
    }

    @PostMapping("/cadastro")
    public String adicionarUsuario(@ModelAttribute("usuario") UsuarioDTO usuarioDTO) {
        try {
            usuarioService.adicionarUsuario(usuarioDTO);
            return "cadastro/cadastroSucesso";
        } catch (Exception e) {
            return "cadastro/cadastroErro";
        }
    }
}
