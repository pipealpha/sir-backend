package com.example.sirbackend.service;

import java.util.HashMap;
import java.util.Map;

import com.example.sirbackend.dto.UsuarioDTO;
import com.example.sirbackend.model.Curso;
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

            // Verificar se o usuário é um estudante ou coordenador e obter o estudanteId
            Long estudanteId = null;
            Long cursoId = null;
            if (usuario.getPerfil() == Usuario.Perfil.Estudante || usuario.getPerfil() == Usuario.Perfil.Coordenador) {
                Estudante estudante = usuario.getEstudante();
                if (estudante != null) {
                    estudanteId = estudante.getIdEstudante();
                }
                assert estudante != null;
                Curso curso = estudante.getCurso();
                if (curso != null) {
                    cursoId = curso.getIdCurso();
                }
            }

            Map<String, Object> response = new HashMap<>();
            response.put("perfil", usuario.getPerfil().name());
            response.put("token", token);
            response.put("estudanteId", estudanteId);
            response.put("cursoId", cursoId);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
