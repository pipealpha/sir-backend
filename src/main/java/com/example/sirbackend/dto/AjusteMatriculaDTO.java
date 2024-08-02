package com.example.sirbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AjusteMatriculaDTO {

    private String tipoSolicitacao;
    private boolean solicitacaoFeitaAnteriormente;
    private String statusSolicitacao;
    private String statusSIGAA;
    private String observacao;
    private Long disciplinaId;
    private Long estudanteId;
}
