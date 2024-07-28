package com.example.sirbackend.service;

import java.util.List;

import com.example.sirbackend.model.AjusteMatricula;
import com.example.sirbackend.repository.AjusteMatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AjusteMatriculaService {

    @Autowired
    private AjusteMatriculaRepository ajusteMatriculaRepository;

    public List<AjusteMatricula> getAllAjustesMatricula() {
        return ajusteMatriculaRepository.findAll();
    }

    public AjusteMatricula getAjusteMatriculaById(Integer id) {
        return ajusteMatriculaRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public AjusteMatricula createAjusteMatricula(AjusteMatricula ajusteMatricula) {
        return ajusteMatriculaRepository.save(ajusteMatricula);
    }

    public AjusteMatricula updateAjusteMatricula(Integer id, AjusteMatricula ajusteMatriculaDetails) {
        AjusteMatricula ajusteMatricula = ajusteMatriculaRepository.findById(Long.valueOf(id)).orElse(null);
        if (ajusteMatricula != null) {
            ajusteMatricula.setTipoSolicitacao(ajusteMatriculaDetails.getTipoSolicitacao());
            ajusteMatricula.setSolicitacaoFeitaAnteriormente(ajusteMatriculaDetails.getSolicitacaoFeitaAnteriormente());
            ajusteMatricula.setStatusSolicitacao(ajusteMatriculaDetails.getStatusSolicitacao());
            ajusteMatricula.setStatusSIGAA(ajusteMatriculaDetails.getStatusSIGAA());
            ajusteMatricula.setObservacao(ajusteMatriculaDetails.getObservacao());
            ajusteMatricula.setDataSolicitacao(ajusteMatriculaDetails.getDataSolicitacao());
            ajusteMatricula.setDisciplina(ajusteMatriculaDetails.getDisciplina());
            ajusteMatricula.setEstudante(ajusteMatriculaDetails.getEstudante());
            return ajusteMatriculaRepository.save(ajusteMatricula);
        } else {
            return null;
        }
    }

    public void deleteAjusteMatricula(Integer id) {
        ajusteMatriculaRepository.deleteById(Long.valueOf(id));
    }

}
