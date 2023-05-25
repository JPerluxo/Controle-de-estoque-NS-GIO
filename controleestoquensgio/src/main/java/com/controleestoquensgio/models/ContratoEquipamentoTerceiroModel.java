package com.controleestoquensgio.models;

import java.sql.Date;

import com.controleestoquensgio.util.SimOuNao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table (name = "CONTRATO_EQUIPAMENTO_TERCEIROS")
public class ContratoEquipamentoTerceiroModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cet_id")
    private int id;

    @Column(name = "cet_fornecedor", nullable = false, length = 50)
    private String fornecedor;

    @Column(name = "cet_dt_inicio", nullable = false)
    private Date dataInicio;

    @Column(name = "cet_dt_termino", nullable = false)
    private Date dataFinal;

    @Column(name = "cet_ativo", nullable = false, length = 4)
    private String ativo;

    public ContratoEquipamentoTerceiroModel () {
        this.ativo = SimOuNao.SIM.name();
    }
}