package dev.nicolas.cadastrodeusuarios.Usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.nicolas.cadastrodeusuarios.Tarefas.model.TarefaModel;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity // Transforma uma classe em uma entidade do DB
@Table(name = "tb_usuarios")
@ToString(exclude = "tarefas")
@EqualsAndHashCode(exclude = "tarefas")
public class UsuarioModel implements UserDetails {

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

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonExpired = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;

    @Column(nullable = false)
    private boolean credentialsNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public enum Role {
        USER, ADMIN
    }

}
