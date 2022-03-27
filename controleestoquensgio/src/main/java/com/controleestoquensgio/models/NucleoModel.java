package com.controleestoquensgio.models;

import javax.persistence.*;

@Entity
@Table (name = "NUCLEOS")
public class NucleoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="nuc_id")
    private int id;

    @Column(name="nuc_descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name="nuc_sigla", nullable = false, length = 50)
    private String sigla;

    @OneToOne
    private GerenciaModel gerencia;
    
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
    public GerenciaModel getGerencia() {
        return gerencia;
    }
    public void setGerencia(GerenciaModel gerencia) {
        this.gerencia = gerencia;
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
}
