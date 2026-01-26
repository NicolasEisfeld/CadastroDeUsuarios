package dev.nicolas.cadastrodeusuarios.Usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // Transforma uma classe em uma entidade do DB
@Table(name = "tb_usuarios")
@ToString(exclude = "tarefas")
@EqualsAndHashCode(exclude = "tarefas")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o valor do ID automaticamente
    @Column(name = "id_usuario")
    private Long id;

    @Column (nullable = false) // Define que a coluna não pode ser nula
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private int idade;

    @OneToMany(mappedBy = "usuario") // um Usuário pode ter mais de uma Tarefa
    @JsonIgnore
    private List<TarefaModel> tarefas;

    private String password;

}
