package com.controleestoquensgio.dtos;

import com.controleestoquensgio.models.TipoEquipamentoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarTipoEquipamentoDto {

    private String descricao;
    private String marca;
    private String modelo;
    private String fornecedor;
    private String polegadas;

    public VisualizarTipoEquipamentoDto(TipoEquipamentoModel equipamentoModel) {
        this.descricao = equipamentoModel.getDescricao();
        this.marca = equipamentoModel.getMarca();
        this.modelo = equipamentoModel.getModelo();
        this.fornecedor = equipamentoModel.getFornecedor();
        this.polegadas = equipamentoModel.getPolegadas();
    }
}
