package com.controleestoquensgio.dtos.RegimeTrabalho;

import com.controleestoquensgio.models.RegimeTrabalhoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarRegimeTrabalhoDto {

    private int id;
    private String descricao;

    public VisualizarRegimeTrabalhoDto(RegimeTrabalhoModel regimeTrabalhoModel) {
        this.id = regimeTrabalhoModel.getId();
        this.descricao = regimeTrabalhoModel.getDescricao();
    }
}