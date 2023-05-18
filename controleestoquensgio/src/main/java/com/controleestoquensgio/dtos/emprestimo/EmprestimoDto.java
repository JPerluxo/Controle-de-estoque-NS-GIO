package com.controleestoquensgio.dtos.emprestimo;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

public class EmprestimoDto {

    @NotNull(message = "Insira uma data de disponibilizacao!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDisponibilizacao;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dataDevolucao;

    @Positive(message = "Insira um colaborador!")
    private int colaboradorId;
    @Positive(message = "Insira um equipamento!")
    private int equipamentoId;
    private int vigente;
    @NotBlank(message = "Insira a finalidade!")
    private String finalidade;
    @Positive(message = "Insira o responsável pela entrega!")
    private int respEntregaId;

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

    public int getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(int colaboradorId) {
        this.colaboradorId = colaboradorId;
    }

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public int getVigente() {
        return vigente;
    }

    public void setVigente(int vigente) {
        this.vigente = vigente;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public int getRespEntregaId() {
        return respEntregaId;
    }

    public void setRespEntregaId(int respEntregaId) {
        this.respEntregaId = respEntregaId;
    }

    public int getVigenteNumero (boolean vigente) {
        if (vigente) {
            return 1;
        } else {
            return 2;
        }
    }
}