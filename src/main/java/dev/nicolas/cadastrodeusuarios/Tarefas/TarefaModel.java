package dev.nicolas.cadastrodeusuarios.Tarefas;
import dev.nicolas.cadastrodeusuarios.Usuario.UsuarioModel;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private int prioridade;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean concluida;

    @ManyToOne // Uma Tarefa pertence a um Usuário
    @JoinColumn(name = "usuario_id") // Define a chave estrangeira
    private UsuarioModel usuario;
}
