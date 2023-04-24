package com.controleestoquensgio.models;

import jakarta.persistence.*;

@Entity
@Table (name = "TIPOS_ACESSO")
public class TipoAcessoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tac_id")
    private int id;

    @Column(name="tac_descricao", nullable = false, length = 200)
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