package dev.nicolas.cadastrodeusuarios.Usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UsuarioController {
    @GetMapping("/boasvindas")
    public String boasVindas() {
        return "Olá mundo!";
    }



}
