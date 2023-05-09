package com.controleestoquensgio.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table (name = "EMPRESTIMOS")
public class EmprestimoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="emp_id")
    private int id;

    @Column(name="emp_dtDisponibilizacao", nullable = false)
    private Date dataDisponibilizacao;
    
    @Column(name="emp_dtDevolucao", nullable = false)
    private Date dataDevolucao;

    @ManyToOne
    private ColaboradorModel colaborador;
    
    @ManyToOne
    private EquipamentoModel equipamento;

    @Column(name="emp_vigente", nullable = false)
    private boolean isVigente;
    
    @Column(name="emp_finalidade", nullable = false, length = 200)
    private String finalidade;
    
    @ManyToOne
    private ColaboradorModel respEntrega;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDataDisponibilizacao() {
        return dataDisponibilizacao;
    }
    public void setDataDisponibilizacao(Date dataDisponibilizacao) {
        this.dataDisponibilizacao = dataDisponibilizacao;
    }
    public Date getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public ColaboradorModel getColaborador() {
        return colaborador;
    }
    public void setColaborador(ColaboradorModel colaborador) {
        this.colaborador = colaborador;
    }
    public EquipamentoModel getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(EquipamentoModel equipamento) {
        this.equipamento = equipamento;
    }
    public boolean isVigente() {
        return isVigente;
    }
    public void setVigente(boolean isVigente) {
        this.isVigente = isVigente;
    }
    public void setVigente(int isVigente) {
        if (isVigente == 1) {
            this.isVigente = true;
        } else {
            this.isVigente = false;
        }
    }
    public String getFinalidade() {
        return finalidade;
    }
    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }
    public ColaboradorModel getRespEntrega() {
        return respEntrega;
    }
    public void setRespEntrega(ColaboradorModel respEntrega) {
        this.respEntrega = respEntrega;
    }
}