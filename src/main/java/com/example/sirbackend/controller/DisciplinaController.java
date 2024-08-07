package com.example.sirbackend.controller;

import java.util.List;

import com.example.sirbackend.model.Disciplina;
import com.example.sirbackend.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    public List<Disciplina> getAllDisciplinas() {
        return disciplinaService.getAllDisciplinas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> getDisciplinaById(@PathVariable Long id) { // Corrigido o tipo de parâmetro para Long
        Disciplina disciplina = disciplinaService.getDisciplinaById(id);
        if (disciplina == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(disciplina);
        }
    }

    @GetMapping("/curso/{id}")
    public ResponseEntity<List<Disciplina>> getDisciplinasByCurso(@PathVariable Long id) {
        List<Disciplina> disciplinas = disciplinaService.findDisciplinasByCursoId(id);
        return ResponseEntity.ok(disciplinas);
    }

    @PostMapping
    public Disciplina createDisciplina(@RequestBody Disciplina disciplina) {
        return disciplinaService.createDisciplina(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> updateDisciplina(@PathVariable Long id, @RequestBody Disciplina disciplinaDetails) {
        Disciplina disciplina = disciplinaService.updateDisciplina(id, disciplinaDetails);
        if (disciplina == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(disciplina);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.deleteDisciplina(id);
        return ResponseEntity.noContent().build();
    }

}
