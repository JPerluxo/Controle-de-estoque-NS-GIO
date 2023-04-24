package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "REGIMES_TRABALHO")
public class RegimeTrabalhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="rgt_id")
    private int id;

    @Column(name="rgt_descricao", nullable = false, length = 200)
    private String descricao;

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
}