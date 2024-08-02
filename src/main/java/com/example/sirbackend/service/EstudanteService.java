package com.example.sirbackend.service;

import java.util.List;

import com.example.sirbackend.model.Curso;
import com.example.sirbackend.model.Estudante;
import com.example.sirbackend.model.Usuario;
import com.example.sirbackend.repository.CursoRepository;
import com.example.sirbackend.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {

    @Autowired
    private EstudanteRepository estudanteRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CursoRepository cursoRepository;

    public List<Estudante> getAllEstudantes() {
        return estudanteRepository.findAll();
    }

    public Estudante getEstudanteById(Integer id) {
        return estudanteRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public Estudante createEstudante(Estudante estudante) {
        // Salvar o usuário primeiro
        Usuario usuario = estudante.getUsuario();
        usuario = usuarioService.createUsuario(usuario);

        // Atribuir o ID do usuário salvo ao estudante
        estudante.setUsuario(usuario);

        // Buscar o curso pelo ID e atribuir ao estudante
        Curso curso = cursoRepository.findById(estudante.getCurso().getIdCurso()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        estudante.setCurso(curso);

        return estudanteRepository.save(estudante);
    }

    public Estudante updateEstudante(Integer id, Estudante estudanteDetails) {
        Estudante estudante = estudanteRepository.findById(Long.valueOf(id)).orElse(null);
        if (estudante != null) {
            estudante.setMatricula(estudanteDetails.getMatricula());
            estudante.setAnoSemestreIngresso(estudanteDetails.getAnoSemestreIngresso());
            estudante.setUsuario(estudanteDetails.getUsuario());
            Curso curso = cursoRepository.findById(estudanteDetails.getCurso().getIdCurso()).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
            estudante.setCurso(curso);
            return estudanteRepository.save(estudante);
        } else {
            return null;
        }
    }

    public void deleteEstudante(Integer id) {
        estudanteRepository.deleteById(Long.valueOf(id));
    }

    public Estudante saveEstudante(Estudante estudante) {
        return estudanteRepository.save(estudante);
    }

}
