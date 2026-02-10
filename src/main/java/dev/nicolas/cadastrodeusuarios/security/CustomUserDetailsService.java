package dev.nicolas.cadastrodeusuarios.security;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));
        
        // Garantir que o usuário tenha pelo menos a role USER
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            usuario.getRoles().add(UsuarioModel.Role.USER);
        }
        
        return usuario;
    }
}
