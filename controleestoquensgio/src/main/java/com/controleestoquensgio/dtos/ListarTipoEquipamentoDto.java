package com.controleestoquensgio.dtos;

import com.controleestoquensgio.models.TipoEquipamentoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarTipoEquipamentoDto {

    private int id;
    private String descricao;
    private String marca;
    private String modelo;
    private String fornecedor;
    private String polegadas;

    public ListarTipoEquipamentoDto(TipoEquipamentoModel tipoEquipamentoModel) {
        this.id = tipoEquipamentoModel.getId();
        this.descricao = tipoEquipamentoModel.getDescricao();
        this.marca = tipoEquipamentoModel.getMarca();
        this.modelo = tipoEquipamentoModel.getModelo();
        this.fornecedor = tipoEquipamentoModel.getFornecedor();
        this.polegadas = tipoEquipamentoModel.getPolegadas();
    }
}
