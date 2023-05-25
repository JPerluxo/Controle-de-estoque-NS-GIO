package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "TIPOS_ACESSO")
public class TipoAcessoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tac_id")
    private int id;

    @Column(name="tac_descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name="tac_ativo", nullable = false, length = 4)
    private String ativo;

    public TipoAcessoModel () {
        this.ativo = SimOuNao.SIM.name();
    }

}