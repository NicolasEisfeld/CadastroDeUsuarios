package dev.nicolas.cadastrodeusuarios.security;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Tentando autenticar usuário com email: {}", email);
        
        UsuarioModel usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Usuário não encontrado com email: {}", email);
                    return new UsernameNotFoundException("Usuário não encontrado com email: " + email);
                });
        
        log.info("Usuário encontrado: {}, com roles: {}", usuario.getEmail(), usuario.getRoles());
        log.info("Senha criptografada no banco: {}", usuario.getPassword());
        
        // Garantir que o usuário tenha pelo menos a role USER
        if (usuario.getRoles() == null || usuario.getRoles().isEmpty()) {
            usuario.getRoles().add(UsuarioModel.Role.USER);
            log.info("Role USER adicionada ao usuário");
        }
        
        log.info("Autoridades do usuário: {}", usuario.getAuthorities());
        
        return usuario;
    }
}
