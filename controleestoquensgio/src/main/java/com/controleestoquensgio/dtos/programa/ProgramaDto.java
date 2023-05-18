package com.controleestoquensgio.dtos.programa;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProgramaDto {

    @NotBlank(message = "Insira uma descrição!")
    private String descricao;

    private String observacao;
    @Positive(message = "Insira uma licença!")
    private int licencaId;

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
    public int getLicencaId() {return licencaId;}
    public void setLicencaId(int licencaId) {this.licencaId = licencaId;}
}
