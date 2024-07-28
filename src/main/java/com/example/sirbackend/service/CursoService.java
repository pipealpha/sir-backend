package com.example.sirbackend.service;

import java.util.List;

import com.example.sirbackend.model.Curso;
import com.example.sirbackend.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getAllCursos() {
        return cursoRepository.findAll();
                //findAllCursos();
    }

    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public Curso createCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, Curso cursoDetails) {
        Curso curso = cursoRepository.findById(id).orElse(null);
        if (curso != null) {
            curso.setNome(cursoDetails.getNome());
            curso.setSigla(cursoDetails.getSigla());
            return cursoRepository.save(curso);
        } else {
            return null;
        }
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }

}
