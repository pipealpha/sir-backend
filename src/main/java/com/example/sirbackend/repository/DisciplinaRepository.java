package com.example.sirbackend.repository;

import java.util.List;

import com.example.sirbackend.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

    List<Disciplina> findByCursoIdCurso(Long idCurso);
}
