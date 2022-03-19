package com.controleestoquensgio.dtos;

import javax.validation.constraints.NotBlank;

public class OcorrenciaDto {

    @NotBlank
    private String os;
    
    @NotBlank
    private String descricao;
    
    private EquipamentoDto equipamento;
    
    private ColaboradorDto colaborador;
    
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
    public EquipamentoDto getEquipamento() {
        return equipamento;
    }
    public void setEquipamento(EquipamentoDto equipamento) {
        this.equipamento = equipamento;
    }
    public ColaboradorDto getColaborador() {
        return colaborador;
    }
    public void setColaborador(ColaboradorDto colaborador) {
        this.colaborador = colaborador;
    }
    
}
