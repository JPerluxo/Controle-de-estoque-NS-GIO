package com.controleestoquensgio.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmprestimoDto {

    @NotNull
    private LocalDate dataDisponibilizacao;
    @NotNull
    private LocalDate dataDevolucao;
    private int colaborador;
    private int equipamento;
    private boolean isVigente;
    @NotBlank
    private String finalidade;
    private int respEntrega;

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

    public int getColaborador() {
        return colaborador;
    }

    public void setColaborador(int colaborador) {
        this.colaborador = colaborador;
    }

    public int getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(int equipamento) {
        this.equipamento = equipamento;
    }

    public boolean isVigente() {
        return isVigente;
    }

    public void setVigente(boolean vigente) {
        isVigente = vigente;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public int getRespEntrega() {
        return respEntrega;
    }

    public void setRespEntrega(int respEntrega) {
        this.respEntrega = respEntrega;
    }
}