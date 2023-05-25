package com.controleestoquensgio.dtos.localizacao;

import com.controleestoquensgio.models.LocalizacaoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarLocalizacaoDto {

    private int id;
    private String predio;
    private String andar;
    private String lado;
    private String referencia;
    private String ativo;
    public ListarLocalizacaoDto(LocalizacaoModel localizacaoModel) {
        this.id = localizacaoModel.getId();
        this.predio = localizacaoModel.getPredio();
        this.andar = localizacaoModel.getAndar();
        this.lado = localizacaoModel.getLado();
        this.referencia = localizacaoModel.getReferencia();
        this.ativo = localizacaoModel.getAtivo();
    }
}
