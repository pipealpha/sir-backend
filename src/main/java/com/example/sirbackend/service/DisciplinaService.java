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

    public Disciplina getDisciplinaById(Integer id) {
        return disciplinaRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public Disciplina createDisciplina(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    public Disciplina updateDisciplina(Integer id, Disciplina disciplinaDetails) {
        Disciplina disciplina = disciplinaRepository.findById(Long.valueOf(id)).orElse(null);
        if (disciplina != null) {
            disciplina.setCodigo(disciplinaDetails.getCodigo());
            disciplina.setNome(disciplinaDetails.getNome());
            disciplina.setCurso(disciplinaDetails.getCurso());
            return disciplinaRepository.save(disciplina);
        } else {
            return null;
        }
    }

    public void deleteDisciplina(Integer id) {
        disciplinaRepository.deleteById(Long.valueOf(id));
    }

}
