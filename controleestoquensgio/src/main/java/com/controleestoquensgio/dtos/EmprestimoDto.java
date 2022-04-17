package com.controleestoquensgio.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.EquipamentoModel;

public class EmprestimoDto {

    @NotBlank
    private Date dataDisponibilizacao;
    
    @NotBlank
    private Date dataDevolucao;
    private ColaboradorModel colaborador;
    private EquipamentoModel equipamento;
    private boolean isVigente;
    
    @NotBlank
    private String finalidade;
    
    private ColaboradorModel respEntrega;

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