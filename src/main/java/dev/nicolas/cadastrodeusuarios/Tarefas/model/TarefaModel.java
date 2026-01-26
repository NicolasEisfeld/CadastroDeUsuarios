package dev.nicolas.cadastrodeusuarios.Tarefas.model;
import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_tarefas")
@ToString(exclude = "usuario")
@EqualsAndHashCode(exclude = "usuario")
public class TarefaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id_tarefa")
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
