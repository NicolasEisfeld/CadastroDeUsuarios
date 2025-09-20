package dev.nicolas.cadastrodeclientes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UsuarioController {

    @GetMapping("/cadastrodeclientes/hello")
    public String hello() {
        return "Olá, mundo!";
    }
}
