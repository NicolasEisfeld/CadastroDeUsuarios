package dev.nicolas.cadastrodeusuarios.security;

import dev.nicolas.cadastrodeusuarios.Usuario.model.UsuarioModel;
import dev.nicolas.cadastrodeusuarios.Usuario.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            
            String jwt = jwtService.generateToken(userDetails);
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            
            UsuarioModel usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

            Map<String, Object> response = new HashMap<>();
            response.put("token", jwt);
            response.put("type", "Bearer");
            response.put("id", usuario.getId());
            response.put("nome", usuario.getNome());
            response.put("email", usuario.getEmail());
            response.put("role", role);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Credenciais inválidas");
            return ResponseEntity.status(401).body(error);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String username = jwtService.extractUsername(jwt);
            
            UsuarioModel usuario = usuarioRepository.findByEmail(username)
                    .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
            
            String newToken = jwtService.generateToken(usuario);
            String role = usuario.getAuthorities().iterator().next().getAuthority();
            
            Map<String, Object> response = new HashMap<>();
            response.put("token", newToken);
            response.put("type", "Bearer");
            response.put("id", usuario.getId());
            response.put("nome", usuario.getNome());
            response.put("email", usuario.getEmail());
            response.put("role", role);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Token inválido");
            return ResponseEntity.status(401).body(error);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Não autenticado");
            return ResponseEntity.status(401).body(error);
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        UsuarioModel usuario = usuarioRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Map<String, Object> response = new HashMap<>();
        response.put("id", usuario.getId());
        response.put("nome", usuario.getNome());
        response.put("email", usuario.getEmail());
        response.put("idade", usuario.getIdade());
        response.put("roles", usuario.getAuthorities());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        SecurityContextHolder.clearContext();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout realizado com sucesso");
        return ResponseEntity.ok(response);
    }
}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
