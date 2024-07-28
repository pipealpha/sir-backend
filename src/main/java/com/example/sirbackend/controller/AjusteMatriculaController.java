package com.example.sirbackend.controller;

import java.util.List;

import com.example.sirbackend.model.AjusteMatricula;
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

    @GetMapping
    public List<AjusteMatricula> getAllAjustesMatricula() {
        return ajusteMatriculaService.getAllAjustesMatricula();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AjusteMatricula> getAjusteMatriculaById(@PathVariable Integer id) {
        AjusteMatricula ajusteMatricula = ajusteMatriculaService.getAjusteMatriculaById(id);
        if (ajusteMatricula == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ajusteMatricula);
        }
    }

    @PostMapping
    public AjusteMatricula createAjusteMatricula(@RequestBody AjusteMatricula ajusteMatricula) {
        return ajusteMatriculaService.createAjusteMatricula(ajusteMatricula);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AjusteMatricula> updateAjusteMatricula(@PathVariable Integer id, @RequestBody AjusteMatricula ajusteMatriculaDetails) {
        AjusteMatricula ajusteMatricula = ajusteMatriculaService.updateAjusteMatricula(id, ajusteMatriculaDetails);
        if (ajusteMatricula == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(ajusteMatricula);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAjusteMatricula(@PathVariable Integer id) {
        ajusteMatriculaService.deleteAjusteMatricula(id);
        return ResponseEntity.noContent().build();
    }

}
