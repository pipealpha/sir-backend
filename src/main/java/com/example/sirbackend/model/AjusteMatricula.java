package com.example.sirbackend.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AJUSTE_MATRICULA")
public class AjusteMatricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ajuste_matricula")
    private Long idAjusteMatricula;

    @Column(name = "tipo_solicitacao")
    private String tipoSolicitacao;

    @Column(name = "solicitacao_feita_anteriormente")
    private boolean solicitacaoFeitaAnteriormente;

    @Column(name = "status_solicitacao")
    private String statusSolicitacao;

    @Column(name = "status_SIGAA")
    private String statusSIGAA;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "data_solicitacao")
    private Date dataSolicitacao;

    @ManyToOne
    @JoinColumn(name = "disciplina_id_disciplina", nullable = false)
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "estudante_id_estudante", nullable = false)
    private Estudante estudante;

    public boolean getSolicitacaoFeitaAnteriormente() {
        return solicitacaoFeitaAnteriormente;
    }

    // Getters and Setters in Lombok

}
