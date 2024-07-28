package com.example.sirbackend.controller;

import java.util.List;

import com.example.sirbackend.model.Estudante;
import com.example.sirbackend.service.EstudanteService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteService estudanteService;

    @GetMapping
    public List<Estudante> getAllEstudantes() {
        return estudanteService.getAllEstudantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> getEstudanteById(@PathVariable Integer id) {
        Estudante estudante = estudanteService.getEstudanteById(id);
        if (estudante == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(estudante);
        }
    }

    @PostMapping
    public Estudante createEstudante(@RequestBody Estudante estudante) {
        return estudanteService.createEstudante(estudante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> updateEstudante(@PathVariable Integer id, @RequestBody Estudante estudanteDetails) {
        Estudante estudante = estudanteService.updateEstudante(id, estudanteDetails);
        if (estudante == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(estudante);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudante(@PathVariable Integer id) {
        estudanteService.deleteEstudante(id);
        return ResponseEntity.noContent().build();
    }
}
