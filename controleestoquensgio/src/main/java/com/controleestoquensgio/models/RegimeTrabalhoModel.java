package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "REGIMES_TRABALHO")
public class RegimeTrabalhoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="rgt_id")
    private int id;

    @Column(name="rgt_descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name="rgt_ativo", nullable = false, length = 4)
    private String ativo;
    public RegimeTrabalhoModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}