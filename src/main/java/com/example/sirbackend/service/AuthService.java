package com.example.sirbackend.service;

import java.util.HashMap;
import java.util.Map;

import com.example.sirbackend.dto.UsuarioDTO;
import com.example.sirbackend.model.Estudante;
import com.example.sirbackend.model.Usuario;
import com.example.sirbackend.repository.UsuarioRepository;
import com.example.sirbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<?> authenticate(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getSenha())) {
            String token = jwtTokenProvider.generateToken(usuario.getEmail());

            // Verificar se o usuário é um estudante e obter o estudanteId
            Long estudanteId = null;
            if (usuario.getPerfil() == Usuario.Perfil.Estudante) {
                Estudante estudante = usuario.getEstudante();
                if (estudante != null) {
                    estudanteId = estudante.getIdEstudante();
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("perfil", usuario.getPerfil().name());
            response.put("token", token);
            response.put("estudanteId", estudanteId);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
