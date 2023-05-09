package com.controleestoquensgio.dtos;

import com.controleestoquensgio.models.EmprestimoModel;
import java.util.Date;

public class ListarEmprestimosDto {

    private int id;
    private Date dataDisponibilizacao;
    private Date dataDevolucao;
    private String colaborador;
    private String equipamento;
    private String isVigente;
    private String finalidade;
    private String respEntrega;

    public ListarEmprestimosDto (EmprestimoModel emprestimoModel) {
        this.id = emprestimoModel.getId();
        this.dataDisponibilizacao = emprestimoModel.getDataDisponibilizacao();
        this.dataDevolucao = emprestimoModel.getDataDevolucao();
        this.colaborador = emprestimoModel.getColaborador().getNome();
        this.equipamento = emprestimoModel.getEquipamento().getNumPatrimonio();
        this.isVigente = getIsVigente(emprestimoModel.isVigente());
        this.finalidade = emprestimoModel.getFinalidade();
        this.respEntrega = emprestimoModel.getRespEntrega().getNome();
    }

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

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getIsVigente() {
        return isVigente;
    }

    public void setIsVigente(String isVigente) {
        this.isVigente = isVigente;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public String getRespEntrega() {
        return respEntrega;
    }

    public void setRespEntrega(String respEntrega) {
        this.respEntrega = respEntrega;
    }

    public String getIsVigente (boolean isVigente) {
        if (isVigente) {
            return "Sim";
        } else {
            return "Nao";
        }
    }
}
