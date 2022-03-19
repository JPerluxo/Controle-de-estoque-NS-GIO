package com.controleestoquensgio.dtos;

import java.util.Date;

import javax.validation.constraints.NotBlank;

public class EmprestimoDto {

    @NotBlank
    private Date dataDisponibilizacao;
    
    @NotBlank
    private Date dataDevolucao;
    private ColaboradorDto colaborador;
    private EquipamentoDto equipamento;
    private boolean isVigente;
    
    @NotBlank
    private String finalidade;
    
    private ColaboradorDto respEntrega;

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

    public ColaboradorDto getColaborador() {
        return colaborador;
    }

    public void setColaborador(ColaboradorDto colaborador) {
        this.colaborador = colaborador;
    }

    public EquipamentoDto getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(EquipamentoDto equipamento) {
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

    public ColaboradorDto getRespEntrega() {
        return respEntrega;
    }

    public void setRespEntrega(ColaboradorDto respEntrega) {
        this.respEntrega = respEntrega;
    }
    
}