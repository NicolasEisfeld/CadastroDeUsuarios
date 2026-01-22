package dev.nicolas.cadastrodeusuarios.Tarefas.dto;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
    private long id;

    private String descricao;

    private int prioridade;

    private boolean concluida;

    private UsuarioModel usuario;
}
