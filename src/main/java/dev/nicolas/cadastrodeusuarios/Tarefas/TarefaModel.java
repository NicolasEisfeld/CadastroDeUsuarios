package dev.nicolas.cadastrodeusuarios.Tarefas;
import dev.nicolas.cadastrodeusuarios.Usuario.UsuarioModel;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tarefas")
public class TarefaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;
    private int prioridade;
    private boolean concluida;
    @ManyToOne // Uma Tarefa pertence a um Usuário
    @JoinColumn(name = "usuario_id") // Define a chave estrangeira
    private UsuarioModel usuario;
}
