package com.controleestoquensgio.dtos.tipoAcesso;

import com.controleestoquensgio.models.TipoAcessoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisualizarTipoAcessoDto {

    private int id;
    private String descricao;

    public VisualizarTipoAcessoDto(TipoAcessoModel tipoAcessoModel) {
        this.id = tipoAcessoModel.getId();
        this.descricao = tipoAcessoModel.getDescricao();
    }
}
