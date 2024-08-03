package com.example.sirbackend.controller;

import java.util.ArrayList;
import java.util.List;

import com.example.sirbackend.dto.AjusteMatriculaDTO;
import com.example.sirbackend.model.AjusteMatricula;
import com.example.sirbackend.repository.AjusteMatriculaRepository;
import com.example.sirbackend.service.AjusteMatriculaService;
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
@RequestMapping("/api/ajuste-matricula")
public class AjusteMatriculaController {

    @Autowired
    private AjusteMatriculaService ajusteMatriculaService;
    @Autowired
    private AjusteMatriculaRepository ajusteMatriculaRepository;

    @PostMapping
    public ResponseEntity<AjusteMatricula> createAjusteMatricula(@RequestBody AjusteMatriculaDTO ajusteMatriculaDTO) {
        AjusteMatricula novoAjusteMatricula = ajusteMatriculaService.createAjusteMatricula(ajusteMatriculaDTO);
        return ResponseEntity.ok(novoAjusteMatricula);
    }

    @GetMapping
    public List<AjusteMatricula> getAllAjusteMatriculas() {
        return ajusteMatriculaService.getAllAjusteMatriculas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AjusteMatricula> getAjusteMatriculaById(@PathVariable Long id) {
        AjusteMatricula ajusteMatricula = ajusteMatriculaService.getAjusteMatriculaById(id);
        if (ajusteMatricula == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ajusteMatricula);
        }
    }

    @GetMapping("/estudante/{estudanteId}")
    public ResponseEntity<List<AjusteMatricula>> getAjustesMatriculaByEstudante(@PathVariable Long estudanteId) {
        List<AjusteMatricula> ajustes = ajusteMatriculaService.findByEstudanteId(estudanteId);
        return ResponseEntity.ok(ajustes);
    }


    @PutMapping("/{id}")
    public ResponseEntity<AjusteMatricula> updateAjusteMatricula(@PathVariable Long id, @RequestBody AjusteMatricula ajusteMatriculaDetails) {
        AjusteMatricula ajusteMatricula = ajusteMatriculaService.updateAjusteMatricula(id, ajusteMatriculaDetails);
        if (ajusteMatricula == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ajusteMatricula);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAjusteMatricula(@PathVariable Long id) {
        ajusteMatriculaService.deleteAjusteMatricula(id);
        return ResponseEntity.noContent().build();
    }
}
