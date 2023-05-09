package com.controleestoquensgio.dtos;

import com.controleestoquensgio.models.OcorrenciaModel;

public class ListarOcorrenciasDto {

    private int id;

    private String os;
    private String descricao;

    private String equipamento;

    private String colaborador;

    public ListarOcorrenciasDto(OcorrenciaModel ocorrenciaModel) {
        this.id = ocorrenciaModel.getId();
        this.os = ocorrenciaModel.getOs();
        this.descricao = ocorrenciaModel.getDescricao();
        this.equipamento = ocorrenciaModel.getEquipamento().getNumPatrimonio();
        this.colaborador = ocorrenciaModel.getColaborador().getNome();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }
}
