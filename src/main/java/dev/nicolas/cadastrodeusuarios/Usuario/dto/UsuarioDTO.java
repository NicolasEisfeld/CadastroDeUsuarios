package dev.nicolas.cadastrodeusuarios.Usuario.dto;

import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

        private Long id;
        private String nome;
        private String email;
        private int idade;
        private List<TarefaModel> tarefas;
        private String password;

    }

}
