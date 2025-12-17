package dev.nicolas.cadastrodeusuarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String email;
    private int idade;
}
