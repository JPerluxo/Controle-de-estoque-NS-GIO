package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "PRESIDENCIAS")
public class PresidenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="pre_id")
    private int id;

    @Column(name="pre_descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name="pre_sigla", nullable = false, length = 50)
    private String sigla;

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
