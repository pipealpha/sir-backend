package com.example.sirbackend.service;

import java.util.List;

import com.example.sirbackend.model.Usuario;
import com.example.sirbackend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(Integer id) {
        return usuarioRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public Usuario createUsuario(Usuario usuario) {
        usuario.setPerfil(Usuario.Perfil.valueOf("Estudante")); // Define o perfil como Estudante
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Integer id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(Long.valueOf(id)).orElse(null);
        if (usuario != null) {
            usuario.setNome(usuarioDetails.getNome());
            usuario.setEmail(usuarioDetails.getEmail());
            usuario.setSenha(usuarioDetails.getSenha());
            usuario.setPerfil(usuarioDetails.getPerfil());
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    public void deleteUsuario(Integer id) {
        usuarioRepository.deleteById(Long.valueOf(id));
    }

}
