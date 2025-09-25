package dev.nicolas.cadastrodeusuarios.Tarefas.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import dev.nicolas.cadastrodeusuarios.Usuarios.Model.UsuarioModel;
import jakarta.persistence.Column;

@Entity // Annotation
@Table(name = "tb_tarefa")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TarefaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private boolean concluida;

    @ManyToOne // Muitas tarefas para um usuário
    @JoinColumn(name = "usuario_id", nullable = false) // Foreign Key
    private UsuarioModel usuario;

}
