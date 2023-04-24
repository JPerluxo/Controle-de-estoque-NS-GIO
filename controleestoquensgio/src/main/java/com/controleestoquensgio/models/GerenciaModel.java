package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "GERENCIAS")
public class GerenciaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ger_id")
    private int id;

    @Column(name="ger_descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name="ger_sigla", nullable = false, length = 50)
    private String sigla;

    @OneToOne
    private DiretoriaModel diretoria;
    
    @OneToOne
    private PresidenciaModel presidencia;

    @OneToOne
    private ColaboradorModel responsavel;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public DiretoriaModel getDiretoria() {
        return diretoria;
    }
    public void setDiretoria(DiretoriaModel diretoria) {
        this.diretoria = diretoria;
    }
    public ColaboradorModel getResponsavel() {
        return responsavel;
    }
    public void setResponsavel(ColaboradorModel responsavel) {
        this.responsavel = responsavel;
    }
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public PresidenciaModel getPresidencia() {
        return presidencia;
    }
    public void setPresidencia(PresidenciaModel presidencia) {
        this.presidencia = presidencia;
    }

}
