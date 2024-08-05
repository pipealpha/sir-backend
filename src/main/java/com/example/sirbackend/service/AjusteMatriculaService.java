package com.example.sirbackend.service;

import java.util.Date;
import java.util.List;

import com.example.sirbackend.dto.AjusteMatriculaDTO;
import com.example.sirbackend.model.AjusteMatricula;
import com.example.sirbackend.model.Disciplina;
import com.example.sirbackend.model.Estudante;
import com.example.sirbackend.repository.AjusteMatriculaRepository;
import com.example.sirbackend.repository.DisciplinaRepository;
import com.example.sirbackend.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjusteMatriculaService {

    @Autowired
    private AjusteMatriculaRepository ajusteMatriculaRepository;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private EstudanteRepository estudanteRepository;

    public AjusteMatricula createAjusteMatricula(AjusteMatriculaDTO ajusteMatriculaDTO) {
        AjusteMatricula ajusteMatricula = new AjusteMatricula();
        ajusteMatricula.setTipoSolicitacao(ajusteMatriculaDTO.getTipoSolicitacao());
        ajusteMatricula.setSolicitacaoFeitaAnteriormente(ajusteMatriculaDTO.isSolicitacaoFeitaAnteriormente());
        ajusteMatricula.setStatusSolicitacao(ajusteMatriculaDTO.getStatusSolicitacao());
        ajusteMatricula.setStatusSIGAA(ajusteMatriculaDTO.getStatusSIGAA());
        ajusteMatricula.setObservacao(ajusteMatriculaDTO.getObservacao());
        ajusteMatricula.setDataSolicitacao(new Date());

        // Obter disciplina e estudante pelo ID
        Disciplina disciplina = disciplinaRepository.findById(ajusteMatriculaDTO.getDisciplinaId()).orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));
        Estudante estudante = estudanteRepository.findById(ajusteMatriculaDTO.getEstudanteId()).orElseThrow(() -> new RuntimeException("Estudante não encontrado"));

        ajusteMatricula.setDisciplina(disciplina);
        ajusteMatricula.setEstudante(estudante);

        return ajusteMatriculaRepository.save(ajusteMatricula);
    }

    public List<AjusteMatricula> getAllAjusteMatriculas() {
        return ajusteMatriculaRepository.findAll();
    }

    public AjusteMatricula getAjusteMatriculaById(Long id) {
        return ajusteMatriculaRepository.findById(id).orElse(null);
    }

    public AjusteMatricula updateAjusteMatricula(Long id, AjusteMatricula ajusteMatriculaDetails) {
        AjusteMatricula ajusteMatricula = ajusteMatriculaRepository.findById(id).orElse(null);
        if (ajusteMatricula != null) {
            ajusteMatricula.setTipoSolicitacao(ajusteMatriculaDetails.getTipoSolicitacao());
            ajusteMatricula.setSolicitacaoFeitaAnteriormente(ajusteMatriculaDetails.getSolicitacaoFeitaAnteriormente());
            ajusteMatricula.setStatusSolicitacao(ajusteMatriculaDetails.getStatusSolicitacao());
            ajusteMatricula.setStatusSIGAA(ajusteMatriculaDetails.getStatusSIGAA());
            ajusteMatricula.setObservacao(ajusteMatriculaDetails.getObservacao());
            ajusteMatricula.setDisciplina(ajusteMatriculaDetails.getDisciplina());
            ajusteMatricula.setEstudante(ajusteMatriculaDetails.getEstudante());
            return ajusteMatriculaRepository.save(ajusteMatricula);
        } else {
            return null;
        }
    }

    public void deleteAjusteMatricula(Long id) {
        ajusteMatriculaRepository.deleteById(id);
    }

    public List<AjusteMatricula> findByEstudanteId(Long estudanteId) {
        return ajusteMatriculaRepository.findByEstudanteIdEstudante(estudanteId);
    }

    public List<AjusteMatricula> findByCursoId(Long cursoId) {
        return ajusteMatriculaRepository.findByDisciplinaCursoIdCurso(cursoId);
    }
}
