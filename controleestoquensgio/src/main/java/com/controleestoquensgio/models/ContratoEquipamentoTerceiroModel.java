package com.controleestoquensgio.models;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table (name = "CONTRATO_EQUIPAMENTO_TERCEIROS")
public class ContratoEquipamentoTerceiroModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cet_id")
    private int id;

    @Column(name="cet_fornecedor", nullable = false, length = 50)
    private String fornecedor;

    @Column(name="cet_dtInicio", nullable = false)
    private Date dataInicio;
    
    @Column(name="cet_dtTermino", nullable = false)
    private Date dataTermino;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFornecedor() {
        return fornecedor;
    }
    public Date getDataTermino() {
        return dataTermino;
    }
    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }
    public Date getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }
}