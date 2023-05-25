package com.controleestoquensgio.models;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "TIPOS_COLABORADOR")
public class TipoColaboradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="tco_id")
    private int id;

    @Column(name="tco_descricao", nullable = false, length = 200)
    private String descricao;

    @Column(name="tco_ativo", nullable = false, length = 4)
    private String ativo;

    public TipoColaboradorModel () {
        this.ativo = SimOuNao.SIM.name();
    }
} 