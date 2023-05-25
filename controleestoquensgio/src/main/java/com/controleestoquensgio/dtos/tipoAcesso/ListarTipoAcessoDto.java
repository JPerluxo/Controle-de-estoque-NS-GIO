package com.controleestoquensgio.dtos.tipoAcesso;

import com.controleestoquensgio.models.TipoAcessoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarTipoAcessoDto {

    private int id;
    private String descricao;
    private String ativo;
    public ListarTipoAcessoDto(TipoAcessoModel tipoAcessoModel) {
        this.id = tipoAcessoModel.getId();
        this.descricao = tipoAcessoModel.getDescricao();
        this.ativo = tipoAcessoModel.getAtivo();
    }
}
