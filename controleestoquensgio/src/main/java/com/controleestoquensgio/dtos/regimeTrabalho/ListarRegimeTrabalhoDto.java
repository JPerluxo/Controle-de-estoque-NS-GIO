package com.controleestoquensgio.dtos.regimeTrabalho;

import com.controleestoquensgio.models.RegimeTrabalhoModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListarRegimeTrabalhoDto {

    private int id;
    private String descricao;
    private String ativo;
    public ListarRegimeTrabalhoDto(RegimeTrabalhoModel regimeTrabalhoModel) {
        this.id = regimeTrabalhoModel.getId();
        this.descricao = regimeTrabalhoModel.getDescricao();
        this.ativo = regimeTrabalhoModel.getAtivo();
    }
}