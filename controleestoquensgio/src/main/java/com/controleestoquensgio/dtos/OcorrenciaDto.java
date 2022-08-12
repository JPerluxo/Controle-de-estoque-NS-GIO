package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

import com.controleestoquensgio.models.ColaboradorModel;
import com.controleestoquensgio.models.EquipamentoModel;

public class OcorrenciaDto {

    @NotBlank(message = "Insira uma OS!")
    private String os;
    
    @NotBlank(message = "Insira uma descrição!")
    private String descricao;
    
    @NotBlank(message = "Insira um equipamento!")
    private EquipamentoModel equipamento;
    
    @NotBlank(message = "Insira um colaborador!")
    private ColaboradorModel colaborador;
    
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
    public EquipamentoModel getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(EquipamentoModel equipamento) {
        this.equipamento = equipamento;
    }
    public ColaboradorModel getColaborador() {
        return colaborador;
    }
    public void setColaborador(ColaboradorModel colaborador) {
        this.colaborador = colaborador;
    }
    
}
