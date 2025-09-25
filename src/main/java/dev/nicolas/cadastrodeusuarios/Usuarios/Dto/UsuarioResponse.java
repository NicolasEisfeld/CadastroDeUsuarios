package dev.nicolas.cadastrodeusuarios.Usuarios.Dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private LocalDateTime dataCriacao;

    public UsuarioResponse(Long id, String nome, String email, LocalDateTime dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
    }
}
