package com.controleestoquensgio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class OcorrenciaDto {

    @NotBlank(message = "Insira uma OS!")
    private String os;
    
    @NotBlank(message = "Insira uma descrição!")
    private String descricao;
    
    @Positive(message = "Insira um equipamento!")
    private int equipamentoId;
    
    @Positive(message = "Insira um colaborador!")
    private int colaboradorId;

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

    public int getEquipamentoId() {
        return equipamentoId;
    }

    public void setEquipamentoId(int equipamentoId) {
        this.equipamentoId = equipamentoId;
    }

    public int getColaboradorId() {
        return colaboradorId;
    }

    public void setColaboradorId(int colaboradorId) {
        this.colaboradorId = colaboradorId;
    }
}
