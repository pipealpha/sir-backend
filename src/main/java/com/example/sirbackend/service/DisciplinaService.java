package com.example.sirbackend.service;

import java.util.List;

import com.example.sirbackend.model.Disciplina;
import com.example.sirbackend.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public List<Disciplina> getAllDisciplinas() {
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaById(Long id) {
        return disciplinaRepository.findById(id).orElse(null);
    }

    public List<Disciplina> findDisciplinasByCursoId(Long cursoId) {
        return disciplinaRepository.findByCursoIdCurso(cursoId);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina updateDisciplina(Long id, Disciplina disciplinaDetails) {
        Disciplina disciplina = disciplinaRepository.findById(id).orElse(null);
        if (disciplina != null) {
            disciplina.setCodigo(disciplinaDetails.getCodigo());
            disciplina.setNome(disciplinaDetails.getNome());
            disciplina.setCurso(disciplinaDetails.getCurso());
            return disciplinaRepository.save(disciplina);
        } else {
            return null;
        }
    }

    public void deleteDisciplina(Long id) {
        disciplinaRepository.deleteById(id);
    }

}
