package dev.nicolas.cadastrodeusuarios.Usuario;

import dev.nicolas.cadastrodeusuarios.Tarefas.TarefaModel;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;
// JPA = Java Persistence API

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // Transforma uma classe em uma entidade do DB
@Table(name = "tb_usuarios")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Gera o valor do ID automaticamente
    private Long id;

    @Column (nullable = false) // Define que a coluna não pode ser nula
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    private int idade;

    @OneToMany(mappedBy = "usuario") // um Usuário pode ter mais de uma Tarefa
    private List<TarefaModel> tarefas;

}
