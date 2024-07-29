package com.example.sirbackend.service;

import com.example.sirbackend.dto.UsuarioDTO;
import com.example.sirbackend.model.Usuario;
import com.example.sirbackend.repository.UsuarioRepository;
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

    public ResponseEntity<?> authenticate(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && passwordEncoder.matches(password, usuario.getSenha())) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuario.getId_usuario(), usuario.getEmail(), usuario.getPerfil().name());
            return ResponseEntity.ok(usuarioDTO);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
