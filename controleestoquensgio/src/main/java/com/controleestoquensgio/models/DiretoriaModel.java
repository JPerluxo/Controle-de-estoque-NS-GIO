package com.controleestoquensgio.models;

import javax.persistence.*;

@Entity
@Table (name = "DIRETORIAS")
public class DiretoriaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="dir_id")
    private int id;

    @Column(name="dir_descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name="dir_sigla", nullable = false, length = 50)
    private String sigla;

    @OneToOne
    private PresidenciaModel presidencia;

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
