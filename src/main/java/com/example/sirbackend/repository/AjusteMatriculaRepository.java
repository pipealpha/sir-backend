package com.example.sirbackend.repository;

import java.util.List;

import com.example.sirbackend.model.AjusteMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AjusteMatriculaRepository extends JpaRepository<AjusteMatricula, Long> {

    List<AjusteMatricula> findByEstudanteIdEstudante(Long estudanteId);

}
