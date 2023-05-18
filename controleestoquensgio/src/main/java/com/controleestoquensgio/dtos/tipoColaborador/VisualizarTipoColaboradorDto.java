package com.controleestoquensgio.dtos.tipoColaborador;

import com.controleestoquensgio.models.TipoColaboradorModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarTipoColaboradorDto {

    private int id;
    private String descricao;

    public VisualizarTipoColaboradorDto(TipoColaboradorModel tipoColaboradorModel) {
        this.id = tipoColaboradorModel.getId();
        this.descricao = tipoColaboradorModel.getDescricao();
    }
}
