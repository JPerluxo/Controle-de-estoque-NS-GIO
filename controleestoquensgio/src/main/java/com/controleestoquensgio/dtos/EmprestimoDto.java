package com.controleestoquensgio.dtos;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.EquipamentoModel;

public class EmprestimoDto {

    @NotNull
    private LocalDate dataDisponibilizacao;
    
    @NotNull
    private LocalDate dataDevolucao;

    private ColaboradorModel colaborador;
    private EquipamentoModel equipamento;
    private boolean isVigente;
    
    @NotBlank
    private String finalidade;
    
    private ColaboradorModel respEntrega;

    public LocalDate getDataDisponibilizacao() {
        return dataDisponibilizacao;
    }

    public void setDataDisponibilizacao(LocalDate dataDisponibilizacao) {
        this.dataDisponibilizacao = dataDisponibilizacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
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