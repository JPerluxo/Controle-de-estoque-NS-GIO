package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "PROGRAMAS")
public class ProgramaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="prg_id")
    private int id;

    @Column(name="prg_descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name="prg_observacao", length = 200)
    private String observacao;
    
    @ManyToOne
    private LicencaModel licenca;

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
    public LicencaModel getLicenca() {
        return licenca;
    }
    public void setLicenca(LicencaModel licenca) {
        this.licenca = licenca;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
