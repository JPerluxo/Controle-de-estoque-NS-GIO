package com.controleestoquensgio.dtos;

import com.controleestoquensgio.models.ProgramaModel;

public class ListarProgramaDto {

    private int id;

    private String descricao;

    private String observacao;

    private String licenca;

    public ListarProgramaDto(ProgramaModel programaModel) {
        this.id = programaModel.getId();
        this.descricao = programaModel.getDescricao();
        this.observacao = programaModel.getObservacao();
        this.licenca = programaModel.getLicenca().getDescricao();
    }

    public ListarProgramaDto() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }
}
